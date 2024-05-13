package com.munshisoft.quranplayer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.munshisoft.quranplayer.R;
import com.munshisoft.quranplayer.helper.GlobalAudioPlayer;
import com.munshisoft.quranplayer.helper.VolleyHelper;
import com.munshisoft.quranplayer.model.QuranResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {
    private Context context;
    private List<QuranResponse.QuranChapter> chapters;
    private OnItemClickListener onItemClickListener;
    private GlobalAudioPlayer audioPlayer;
    int clickedIndex = -1;
    public int recitationID = 3;
    SurahViewHolder lastHolder;

    public SurahAdapter(Context context, List<QuranResponse.QuranChapter> chapters, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.chapters = chapters;
        this.onItemClickListener = onItemClickListener;
        audioPlayer = GlobalAudioPlayer.getInstance();
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, final int position) {
        QuranResponse.QuranChapter chapter = chapters.get(position);
        holder.surahNumber.setText(String.valueOf(chapter.getId())+". ");
        holder.surahName.setText(chapter.getNameSimple());
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(position, chapter));
        holder.btnPlayPause.setOnClickListener(v -> {
            if(clickedIndex == position){
                if (audioPlayer.isPlaying()) {
                    audioPlayer.pause();
                    holder.btnPlayPause.setImageResource(R.drawable.ic_play);
                }else{
                    audioPlayer.play();
                    holder.btnPlayPause.setImageResource(R.drawable.ic_pause);
                }
            }else {
                if(lastHolder != null){
                    lastHolder.btnPlayPause.setImageResource(R.drawable.ic_play);
                    audioPlayer.stop();
                }
                    VolleyHelper.getInstance(context).getRequest("https://api.quran.com/api/v4/chapter_recitations/" + recitationID + "/" + chapter.getId(), new VolleyHelper.VolleyCallback() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e("Response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject audioFile = jsonObject.getJSONObject("audio_file");
                                String audioUrl = audioFile.getString("audio_url");
                                audioPlayer.play(audioUrl, () -> holder.btnPlayPause.setImageResource(R.drawable.ic_play));
                                holder.btnPlayPause.setImageResource(R.drawable.ic_pause);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Failed to get audio URL\n" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Toast.makeText(context, "ERROR:\n" + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
            }
            clickedIndex = position;
            lastHolder = holder;
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder {
        TextView surahNumber;
        TextView surahName;
        ImageView btnPlayPause;

        @SuppressLint("WrongViewCast")
        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            surahNumber = itemView.findViewById(R.id.surah_number);
            surahName = itemView.findViewById(R.id.surah_name);
            btnPlayPause = itemView.findViewById(R.id.btn_play_pause);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, QuranResponse.QuranChapter chapter);
    }
}


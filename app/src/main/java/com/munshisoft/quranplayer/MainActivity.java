package com.munshisoft.quranplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.munshisoft.quranplayer.adapter.RecitationSpinnerAdapter;
import com.munshisoft.quranplayer.adapter.SurahAdapter;
import com.munshisoft.quranplayer.databinding.ActivityMainBinding;
import com.munshisoft.quranplayer.helper.VolleyHelper;
import com.munshisoft.quranplayer.model.QuranResponse;
import com.munshisoft.quranplayer.model.RecitationResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<QuranResponse.QuranChapter> chapters;
    SurahAdapter surahAdapter;
    RecitationSpinnerAdapter recitationSpinnerAdapter;
    RecitationResponse recitationResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        setContentView(binding.getRoot());
        getListOfSurah();
        getListRecitation();
    }
    private void getListRecitation(){
        VolleyHelper.getInstance(this).getRequest("https://api.quran.com/api/v4/resources/recitations", new VolleyHelper.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                recitationResponse = new Gson().fromJson(response, RecitationResponse.class);
                recitationSpinnerAdapter = new RecitationSpinnerAdapter(MainActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, recitationResponse.getRecitations());
                binding.spinner.setAdapter(recitationSpinnerAdapter);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(surahAdapter != null && recitationResponse != null)
                    surahAdapter.recitationID = recitationResponse.getRecitations().get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void getListOfSurah(){
        VolleyHelper.getInstance(this).getRequest("https://api.quran.com/api/v4/chapters", new VolleyHelper.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e("Response fo Chapters", response);
                Gson gson = new Gson();
                QuranResponse quranResponse = gson.fromJson(response, QuranResponse.class);
                chapters = new ArrayList<>();
                chapters = quranResponse.getChapters();
                surahAdapter = new SurahAdapter(MainActivity.this, chapters, (position, chapter) -> {

                });
                Log.e("Chapter List SIze", "Size of Chapter Array: "+chapters.size());
                binding.homeRcv.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
                binding.homeRcv.setAdapter(surahAdapter);
            }
            @Override
            public void onError(String errorMessage) {
                Log.e("Error", errorMessage);
            }
        });
    }
}
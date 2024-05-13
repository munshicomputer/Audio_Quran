package com.munshisoft.quranplayer.helper;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.IOException;

public class GlobalAudioPlayer {
    private static GlobalAudioPlayer instance;
    private MediaPlayer mediaPlayer;

    private GlobalAudioPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());
    }

    public static GlobalAudioPlayer getInstance() {
        if (instance == null) {
            instance = new GlobalAudioPlayer();
        }
        return instance;
    }

    public void play(String url, AudioPlayOnCompleteListener listener) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());
            mediaPlayer.setOnCompletionListener(mediaPlayer -> listener.onComplete());
        } catch (IOException e) {
            Log.e("GlobalAudioPlayer", "Error playing audio", e);
        }
    }
    public interface AudioPlayOnCompleteListener{
        void onComplete();
    }

    public void play() {
        if (mediaPlayer != null) {
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }
    }
    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }else{
            mediaPlayer.reset();
        }
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
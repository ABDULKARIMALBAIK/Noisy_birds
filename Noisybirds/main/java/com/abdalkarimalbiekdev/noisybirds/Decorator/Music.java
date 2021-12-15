package com.abdalkarimalbiekdev.noisybirds.Decorator;

import android.content.Context;
import android.media.MediaPlayer;

import com.abdalkarimalbiekdev.noisybirds.R;

public class Music extends MusicDecoartor {

    private MediaPlayer mediaPlayer;

    public Music(MusicInterface musicInterface, Context context){
        super(musicInterface);
        mediaPlayer=MediaPlayer.create( context, R.raw.background );
    }

    @Override
    public void start() {
        super.start();
        mediaPlayer.start();
    }

    @Override
    public void pausee() {
        super.pausee();
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        super.stop();
        mediaPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        super.isPlaying();
        return mediaPlayer.isPlaying();
    }
}

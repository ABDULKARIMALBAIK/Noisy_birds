package com.abdalkarimalbiekdev.noisybirds.Decorator;

public class MusicDecoartor implements MusicInterface{

    private MusicInterface musicInterface;

    public MusicDecoartor(MusicInterface musicInterface) {
        this.musicInterface=musicInterface;
    }

    @Override
    public void start() {
        musicInterface.start();
    }

    @Override
    public void pausee() {
        musicInterface.pausee();
    }

    @Override
    public void stop() {
        musicInterface.stop();
    }

    @Override
    public boolean isPlaying() {
        musicInterface.isPlaying();
        return musicInterface.isPlaying();
    }
}

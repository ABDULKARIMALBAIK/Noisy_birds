package com.abdalkarimalbiekdev.noisybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

import com.abdalkarimalbiekdev.noisybirds.Decorator.Music;
import com.abdalkarimalbiekdev.noisybirds.Decorator.MusicInterface;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private MusicInterface musicInterface;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);

        prefs = getSharedPreferences("game", MODE_PRIVATE);

        musicInterface = new Music( gameView,this );

        if (!prefs.getBoolean("isMute", false))
            musicInterface.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();

        if (musicInterface.isPlaying())
            musicInterface.pausee();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();

        if (!prefs.getBoolean("isMute", false))
            musicInterface.start();

    }
}

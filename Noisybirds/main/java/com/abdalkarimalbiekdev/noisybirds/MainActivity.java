package com.abdalkarimalbiekdev.noisybirds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdalkarimalbiekdev.noisybirds.Adapter.LevelAdapter;
import com.abdalkarimalbiekdev.noisybirds.Model.Level;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    RecyclerView recyclerLevels;
    TextView highScoreTxt;
    TextView highChickenTxt;
    TextView txtTitle;

    List<Level> levels;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        initViews();
        setupFont();

        prefs  = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTxt.setText(String.valueOf(prefs.getInt("highscore", 0)));
        highChickenTxt.setText(String.valueOf(prefs.getInt("highChicken", 0)));

        isMute = prefs.getBoolean("isMute", false);

        final ImageView volumeCtrl = findViewById(R.id.volumeCtrl);

        if (isMute)
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
        else
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isMute = !isMute;
                if (isMute)
                    volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
                else
                    volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();

            }
        });


        initListLevels();

        recyclerLevels.setHasFixedSize(true);
        recyclerLevels.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        recyclerLevels.setAdapter(new LevelAdapter(this , levels));

    }

    @Override
    protected void onResume() {
        super.onResume();

        prefs  = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTxt.setText(String.valueOf(prefs.getInt("highscore", 0)));
        highChickenTxt.setText(String.valueOf(prefs.getInt("highChicken", 0)));

        initListLevels();

        recyclerLevels.setHasFixedSize(true);
        recyclerLevels.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        recyclerLevels.setAdapter(new LevelAdapter(this , levels));
    }

    private void setupFont() {

        Typeface typeface = Typeface.createFromAsset(getAssets() , "fonts/giddyupstd.otf");
        txtTitle.setTypeface(typeface);
        highScoreTxt.setTypeface(typeface);
        highChickenTxt.setTypeface(typeface);
    }

    private void initViews() {
        highScoreTxt = findViewById(R.id.highScoreTxt);
        highChickenTxt = findViewById(R.id.highChickenTxt);
        recyclerLevels = findViewById(R.id.recyclerLevels);
        txtTitle = findViewById(R.id.txtTitle);
    }

    private void initListLevels() {

        levels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            switch (i){
                case 0:{
                    levels.add(new Level(i , "Level" + " " + (i + 1) , true));
                    break;
                }
                case 1:{
                    if (prefs.getInt("highscore", 0) >= 50 && prefs.getInt("highChicken", 0) >= 50)
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , true));
                    else
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , false));
                    break;
                }
                case 2:{
                    if (prefs.getInt("highscore", 0) >= 100 && prefs.getInt("highChicken", 0) >= 100)
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , true));
                    else
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , false));
                    break;
                }
                case 3:{
                    if (prefs.getInt("highscore", 0) >= 150 && prefs.getInt("highChicken", 0) >= 150)
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , true));
                    else
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , false));
                    break;
                }
                case 4:{
                    if (prefs.getInt("highscore", 0) >= 200 && prefs.getInt("highChicken", 0) >= 200)
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , true));
                    else
                        levels.add(new Level(i , "Level" + " "  + (i + 1) , false));
                    break;
                }
            }
        }
    }
}

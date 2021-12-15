package com.abdalkarimalbiekdev.noisybirds.Singlton_Composite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.abdalkarimalbiekdev.noisybirds.GameView;
import com.abdalkarimalbiekdev.noisybirds.GameView2;
import com.abdalkarimalbiekdev.noisybirds.GameView3;
import com.abdalkarimalbiekdev.noisybirds.GameView4;
import com.abdalkarimalbiekdev.noisybirds.GameView5;
import com.abdalkarimalbiekdev.noisybirds.R;

import java.util.ArrayList;
import java.util.List;


public abstract class Component {

    public int toShoot = 0;
    public int x;
    public int y;
    public int width;
    public int height;
    public int wingCounter = 0;
    public int shootCounter = 1;

    protected Bitmap flight1, flight2, shoot1, shoot2, shoot3, shoot4, shoot5, dead;
    protected GameView gameView;
    protected GameView2 gameView2;
    protected GameView3 gameView3;
    protected GameView4 gameView4;
    protected GameView5 gameView5;

    protected float selectedScreenRatioX;
    protected float selectedScreenRatioY;


    public Component(GameView gameView, int screenY, Resources res) {
        buildBitmaps(gameView,screenY,res);
    }

    public Component(GameView2 gameView2, int screenY, Resources res) {
        buildBitmaps(gameView2,screenY,res);
    }

    public Component(GameView3 gameView3, int screenY, Resources res) {
        buildBitmaps(gameView3,screenY,res);
    }

    public Component(GameView4 gameView4, int screenY, Resources res) {
        buildBitmaps(gameView4,screenY,res);
    }

    public Component(GameView5 gameView5, int screenY, Resources res) {
        buildBitmaps(gameView5,screenY,res);
    }

    public abstract void setupData(GameView gameView, int screenY, Resources res);
    public abstract void setupData(GameView2 gameView2, int screenY, Resources res);
    public abstract void setupData(GameView3 gameView3, int screenY, Resources res);
    public abstract void setupData(GameView4 gameView4, int screenY, Resources res);
    public abstract void setupData(GameView5 gameView5, int screenY, Resources res);

    public abstract Bitmap getFlight();

    public abstract Rect getCollisionShape();

    public abstract Bitmap getDead();

    public abstract void checkScreenRatio();

    public abstract void buildBitmaps(GameView gameView, int screenY, Resources res);
    public abstract void buildBitmaps(GameView2 gameView2, int screenY, Resources res);
    public abstract void buildBitmaps(GameView3 gameView3, int screenY, Resources res);
    public abstract void buildBitmaps(GameView4 gameView4, int screenY, Resources res);
    public abstract void buildBitmaps(GameView5 gameView5, int screenY, Resources res);

    public abstract void add(Component component);
    public abstract void remove(Component component);





















//    public void addFlight(Flight flight){
//        subFlights.add(flight);
//    }
    public List<Component> subFlights = new ArrayList<>();

}

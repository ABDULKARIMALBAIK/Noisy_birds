package com.abdalkarimalbiekdev.noisybirds.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.abdalkarimalbiekdev.noisybirds.R;

import static com.abdalkarimalbiekdev.noisybirds.GameView.screenRatioX;
import static com.abdalkarimalbiekdev.noisybirds.GameView.screenRatioY;

import static com.abdalkarimalbiekdev.noisybirds.GameView2.screenRatioX2;
import static com.abdalkarimalbiekdev.noisybirds.GameView2.screenRatioY2;

import static com.abdalkarimalbiekdev.noisybirds.GameView3.screenRatioX3;
import static com.abdalkarimalbiekdev.noisybirds.GameView3.screenRatioY3;

import static com.abdalkarimalbiekdev.noisybirds.GameView4.screenRatioX4;
import static com.abdalkarimalbiekdev.noisybirds.GameView4.screenRatioY4;

import static com.abdalkarimalbiekdev.noisybirds.GameView5.screenRatioX5;
import static com.abdalkarimalbiekdev.noisybirds.GameView5.screenRatioY5;

public class GameBlood {

    public int x;
    public int y;
    public int width;
    public int height;
    public Bitmap blood;

    public float selectedScreenRatioX;
    public float selectedScreenRatioY;

    public GameBlood(Resources res , int type) {

        checkScreenRatio();

        if (type == 0) //Border
            blood = BitmapFactory.decodeResource(res, R.drawable.blood_border);
        else if (type == 1) //Fill
            blood = BitmapFactory.decodeResource(res, R.drawable.blood_fill);

        width = blood.getWidth();
        height = blood.getHeight();
        width /= 10;
        height /= 10;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        blood = Bitmap.createScaledBitmap(blood, width, height, false);

    }

    public void changeBlood(Resources res , int type){

        if (type == 0) //Border
            blood = BitmapFactory.decodeResource(res, R.drawable.blood_border);
        else if (type == 1) //Fill
            blood = BitmapFactory.decodeResource(res, R.drawable.blood_fill);

        width = blood.getWidth();
        height = blood.getHeight();
        width /= 10;
        height /= 10;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        blood = Bitmap.createScaledBitmap(blood, width, height, false);

    }

    public Bitmap getBlood() {
        return blood;
    }

    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    private void checkScreenRatio() {

        if (screenRatioX > 0 && screenRatioY > 0){
            selectedScreenRatioX = screenRatioX;
            selectedScreenRatioY = screenRatioY;
        }
        else if (screenRatioX2 > 0 && screenRatioY2 > 0){
            selectedScreenRatioX = screenRatioX2;
            selectedScreenRatioY = screenRatioY2;
        }
        else if (screenRatioX3 > 0 && screenRatioY3 > 0){
            selectedScreenRatioX = screenRatioX3;
            selectedScreenRatioY = screenRatioY3;
        }
        else if (screenRatioX4 > 0 && screenRatioY4 > 0){
            selectedScreenRatioX = screenRatioX4;
            selectedScreenRatioY = screenRatioY4;
        }
        else if (screenRatioX5 > 0 && screenRatioY5 > 0){
            selectedScreenRatioX = screenRatioX5;
            selectedScreenRatioY = screenRatioY5;
        }

    }
}

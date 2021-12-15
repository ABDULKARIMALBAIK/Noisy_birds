package com.abdalkarimalbiekdev.noisybirds.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.abdalkarimalbiekdev.noisybirds.R;

import java.util.Random;

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

public class Bullet {

    public int x;
    public int y;
    public int width;
    public int height;
    public Bitmap photo;

    public float selectedScreenRatioX;
    public float selectedScreenRatioY;

    Resources res;

    public Bullet(Resources res) {

        this.res = res;

        checkScreenRatio();

        /////Old Code
        int value = new Random().nextInt(3);
        switch (value){
            case 0:{
                photo = BitmapFactory.decodeResource(res, R.drawable.bullet);
                break;
            }
            case 1:{
                photo = BitmapFactory.decodeResource(res, R.drawable.new_laser);
                break;
            }
            case 2:{
                photo = BitmapFactory.decodeResource(res, R.drawable.new_laser2);
                break;
            }
        }

        width = photo.getWidth();
        height = photo.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        photo = Bitmap.createScaledBitmap(photo, width, height, false);

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

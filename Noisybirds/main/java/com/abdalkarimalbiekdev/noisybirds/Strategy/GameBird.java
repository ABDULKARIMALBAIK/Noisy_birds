package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import com.abdalkarimalbiekdev.noisybirds.R;
import com.abdalkarimalbiekdev.noisybirds.Strategy.ICreateImage;

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


public class GameBird {

    public int speed = 60;
    public boolean wasShot = true;

    public int x = 0;
    public int y;
    public int width;
    public int height;

    public int bloodBird = 0;

    public Bitmap new_bird, new_bird2, new_bird3, new_bird4, new_bird5 , new_bird6 , bird;
    public Bitmap selectedBird;

    public float selectedScreenRatioX;
    public float selectedScreenRatioY;

    public ICreateImage iCreateImage;

    public GameBird(Resources res , float screenRatioX , float screenRatioY , Bitmap bitmap , ICreateImage iCreateImage) {

        //checkScreenRatio();

        selectedScreenRatioX = screenRatioX;
        selectedScreenRatioY = screenRatioY;

        this.iCreateImage = iCreateImage;

        selectedBird = bitmap;

        width = selectedBird.getWidth();
        height = selectedBird.getHeight();
        width /= 8;
        height /= 8;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        selectedBird = Bitmap.createScaledBitmap(selectedBird, width, height, false);
        y = -height;

        speed = iCreateImage.buildSpeed();
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


    public Bitmap getBird() {

        return selectedBird;

        //Old Code
//        if (birdCounter == 1) {
//            birdCounter++;
//            return bird1;
//        }
//
//        if (birdCounter == 2) {
//            birdCounter++;
//            return bird2;
//        }
//
//        if (birdCounter == 3) {
//            birdCounter++;
//            return bird3;
//        }
//
//        birdCounter = 1;
//
//        return bird4;
    }

    public void chickenLeg(Resources res){

        selectedBird = BitmapFactory.decodeResource(res, R.drawable.chicken_leg);

        width = selectedBird.getWidth();
        height = selectedBird.getHeight();

        width /= 8;
        height /= 8;

        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        selectedBird = Bitmap.createScaledBitmap(selectedBird, width, height, false);
    }

    public void changeBird(Resources res){

        int value = new Random().nextInt(7);

        switch (value){
            case 0:{
                bird = BitmapFactory.decodeResource(res, R.drawable.bird1);
                selectedBird = bird;
                break;
            }
            case 1:{
                new_bird = BitmapFactory.decodeResource(res, R.drawable.new_bird);
                selectedBird = new_bird;
                break;
            }
            case 2:{
                new_bird2 = BitmapFactory.decodeResource(res, R.drawable.new_bird2);
                selectedBird = new_bird2;
                break;
            }
            case 3:{
                new_bird3 = BitmapFactory.decodeResource(res, R.drawable.new_bird3);
                selectedBird = new_bird3;
                break;
            }
            case 4:{
                new_bird4 = BitmapFactory.decodeResource(res, R.drawable.new_bird4);
                selectedBird = new_bird4;
                break;
            }
            case 5:{
                new_bird5 = BitmapFactory.decodeResource(res, R.drawable.new_bird5);
                selectedBird = new_bird5;
                break;
            }
            case 6:{
                new_bird6 = BitmapFactory.decodeResource(res, R.drawable.new_bird6);
                selectedBird = new_bird6;
                break;
            }
        }

        width = selectedBird.getWidth();
        height = selectedBird.getHeight();

        width /= 8;
        height /= 8;

        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        selectedBird = Bitmap.createScaledBitmap(selectedBird, width, height, false);
    }

    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

}

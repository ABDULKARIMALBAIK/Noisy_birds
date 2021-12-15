package com.abdalkarimalbiekdev.noisybirds.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.R;

public class Background {

    public int x = 0;
    public int y = 0;
    public Bitmap background;

    public Background(int screenX, int screenY, Resources res , int levelNO) {

        switch (levelNO){
            case 0:{
                background = BitmapFactory.decodeResource(res, R.drawable.background);
                background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
                break;
            }
            case 1:{
                background = BitmapFactory.decodeResource(res, R.drawable.background2);
                background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
                break;
            }
            case 2:{
                background = BitmapFactory.decodeResource(res, R.drawable.background3);
                background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
                break;
            }
            case 3:{
                background = BitmapFactory.decodeResource(res, R.drawable.background4);
                background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
                break;
            }
            case 4:{
                background = BitmapFactory.decodeResource(res, R.drawable.background5);
                background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
                break;
            }

        }

    }

}

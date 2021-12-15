package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.Strategy.GameBird;
import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdThree extends GameBird implements ICreateImage{

    public BirdThree(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.new_bird2),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 20;
                    }
                });
    }


    @Override
    public int buildSpeed() {
        return 20;
    }
}

package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.Strategy.GameBird;
import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdSeven extends GameBird implements ICreateImage{

    public BirdSeven(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.new_bird6),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 40;
                    }
                });
    }


    @Override
    public int buildSpeed() {
        return 40;
    }
}

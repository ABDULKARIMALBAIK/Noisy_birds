package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.Strategy.GameBird;
import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdSix extends GameBird implements ICreateImage{


    public BirdSix(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.new_bird5),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 35;
                    }
                });
    }


    @Override
    public int buildSpeed() {
        return 35;
    }
}

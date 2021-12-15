package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdFive extends GameBird implements ICreateImage{


    public BirdFive(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.new_bird4),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 30;
                    }
                });
    }


    @Override
    public int buildSpeed() {
        return 0;
    }
}

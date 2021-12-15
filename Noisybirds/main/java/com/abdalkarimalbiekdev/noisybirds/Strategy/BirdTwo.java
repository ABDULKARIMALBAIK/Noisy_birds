package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdTwo extends GameBird implements ICreateImage{

    public BirdTwo(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.new_bird),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 15;
                    }
                });
    }


    @Override
    public int buildSpeed() {
        return 15;
    }
}

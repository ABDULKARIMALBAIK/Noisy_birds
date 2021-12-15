package com.abdalkarimalbiekdev.noisybirds.Strategy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abdalkarimalbiekdev.noisybirds.R;

public class BirdOne extends GameBird implements ICreateImage{

    public ICreateImage iCreateImage = this;
    public Bitmap bird;

    public BirdOne(Resources res, float screenRatioX, float screenRatioY) {
        super(res,
                screenRatioX,
                screenRatioY,
                BitmapFactory.decodeResource(res, R.drawable.bird1),
                new ICreateImage() {
                    @Override
                    public int buildSpeed() {
                        return 10;
                    }
                });

    }


    @Override
    public int buildSpeed() {
        return 10;
    }
}

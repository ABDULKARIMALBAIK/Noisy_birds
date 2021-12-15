package com.abdalkarimalbiekdev.noisybirds.Template;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public abstract class Template extends SurfaceView implements ITemplateInterface {

    public Template(Context context) {
        super(context);
    }

    public Template(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Template(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Template(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public abstract void draw();
    @Override
    public abstract void update();
    @Override
    public void sleep(){
        try {
            Thread.sleep(17);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

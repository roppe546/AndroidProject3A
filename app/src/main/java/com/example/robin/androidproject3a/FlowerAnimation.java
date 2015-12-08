package com.example.robin.androidproject3a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Peonsson on 07/12/15.
 */
public class FlowerAnimation extends View {

    Resources res;
    Drawable f1;
    Drawable f2;
    Drawable f3;
    Drawable f4;
    Drawable f5;
    Drawable f6;
    Drawable f7;
    Drawable f8;
    Drawable nopetals;

    public FlowerAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = getContext().getResources();
        nopetals = res.getDrawable(R.drawable.nopetals);
        this.setBackground(nopetals);
    }

    public void drawFlower(double y) {
        // Portrait
        if (y <= -80) {
            f1 = res.getDrawable(R.drawable.f1);
            this.setBackground(f1);
        }
        else if (y <= -70) {
            f2 = res.getDrawable(R.drawable.f2);
            this.setBackground(f2);
        }
        else if (y <= -60) {
            f3 = res.getDrawable(R.drawable.f3);
            this.setBackground(f3);
        }
        else if (y <= -50) {
            f4 = res.getDrawable(R.drawable.f4);
            this.setBackground(f4);
        }
        else if (y <= -40) {
            f5 = res.getDrawable(R.drawable.f5);
            this.setBackground(f5);
        }
        else if (y <= -30) {
            f6 = res.getDrawable(R.drawable.f6);
            this.setBackground(f6);
        }
        else if (y <= -20) {
            f7 = res.getDrawable(R.drawable.f7);
            this.setBackground(f7);
        }
        else if (y <= -10) {
            f8 = res.getDrawable(R.drawable.f8);
            this.setBackground(f8);
        }
        // Landscape
        else if (y <= 10) {
            f8 = res.getDrawable(R.drawable.f8);
            this.setBackground(f8);
        }
        else {
            nopetals = res.getDrawable(R.drawable.nopetals);
            this.setBackground(nopetals);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}

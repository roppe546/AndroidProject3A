package com.example.robin.androidproject3a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Peonsson on 07/12/15.
 */
public class FlowerAnimation extends View {

    private Animation petals;
    private Bitmap background0;
    private Bitmap background1;
    private Bitmap background2;
    private Bitmap background3;
    private Bitmap background4;
    private Bitmap background5;
    private Bitmap background6;
    private Bitmap background7;
    private Bitmap background8;
    private int _background = 1;

    private ImageView petal;

    public FlowerAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        Bitmap background0temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.nopetals);
        background0 = Bitmap.createScaledBitmap(background0temp, width, height, false);
        background0temp.recycle();

        Bitmap background1temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f1);
        background1 = Bitmap.createScaledBitmap(background1temp, width, height, false);
        background1temp.recycle();

        Bitmap background2temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f2);
        background2 = Bitmap.createScaledBitmap(background2temp, width, height, false);
        background2temp.recycle();

        Bitmap background3temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f3);
        background3 = Bitmap.createScaledBitmap(background3temp, width, height, false);
        background3temp.recycle();

        Bitmap background4temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f4);
        background4 = Bitmap.createScaledBitmap(background4temp, width, height, false);
        background4temp.recycle();

        Bitmap background5temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f5);
        background5 = Bitmap.createScaledBitmap(background5temp, width, height, false);
        background5temp.recycle();

        Bitmap background6temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f6);
        background6 = Bitmap.createScaledBitmap(background6temp, width, height, false);
        background6temp.recycle();

        Bitmap background7temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f7);
        background7 = Bitmap.createScaledBitmap(background7temp, width, height, false);
        background7temp.recycle();

        Bitmap background8temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.f8);
        background8 = Bitmap.createScaledBitmap(background8temp, width, height, false);
        background8temp.recycle();

        petal = (ImageView) findViewById(R.id.petal);

        petals = AnimationUtils.loadAnimation(getContext(), R.anim.nopetals);
    }

    public void drawFlower(double y) {
        if (y <= -80) {
            _background = 1;
//            Log.i("DRAWFLOWER", "f1");
        }
        else if (y <= -70) {
            _background = 2;
//            Log.i("DRAWFLOWER", "f2");
        }
        else if (y <= -60) {
            _background = 3;
//            Log.i("DRAWFLOWER", "f3");
        }
        else if (y <= -50) {
            _background = 4;
//            Log.i("DRAWFLOWER", "f4");
        }
        else if (y <= -40) {
            _background = 5;
//            Log.i("DRAWFLOWER", "f5");
        }
        else if (y <= -30) {
            _background = 6;
//            Log.i("DRAWFLOWER", "f6");
        }
        else if (y <= -20) {
            _background = 7;
//            Log.i("DRAWFLOWER", "f7");
        }
        else if (y <= -10) {
            _background = 8;
//            Log.i("DRAWFLOWER", "f8");
        }
        else if (y <= 10) {
            _background = 8;
//            Log.i("DRAWFLOWER", "f8");
        } else {
            _background = 0;
//            Log.i("DRAWFLOWER", "nopetals");
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        System.out.println("running onDraw");

        if(_background == 1) {
            canvas.drawBitmap(background1, 0, 0, null);
        } else if (_background == 2) {
            canvas.drawBitmap(background2, 0, 0, null);
        } else if (_background == 3) {
            canvas.drawBitmap(background3, 0, 0, null);
        } else if (_background == 4) {
            canvas.drawBitmap(background4, 0, 0, null);
        } else if (_background == 5) {
            canvas.drawBitmap(background5, 0, 0, null);
        } else if (_background == 6) {
            canvas.drawBitmap(background6, 0, 0, null);
        } else if (_background == 7) {
            canvas.drawBitmap(background7, 0, 0, null);
        } else if (_background == 8) {
            canvas.drawBitmap(background8, 0, 0, null);
        } else if (_background == 9) {
            canvas.drawBitmap(background8, 0, 0, null);
        } else {
            canvas.drawBitmap(background0, 0, 0, null);
        }
    }

    public void playShake() {
        _background = 0;
//        petal.startAnimation(petals);
    }
}

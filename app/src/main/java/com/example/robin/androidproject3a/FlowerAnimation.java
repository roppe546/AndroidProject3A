package com.example.robin.androidproject3a;

import android.content.Context;
import android.graphics.Canvas;
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

    public FlowerAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);

        LinearLayout mLinearLayout = new LinearLayout(getContext());

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(getContext());
        i.setImageResource(R.drawable.f1);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(new Gallery.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view
       mLinearLayout.addView(i);
    }

    public static void drawFlower(double y) {
        // Portrait
        if (y <= -80) {

        }
        else if (y <= -70) {

        }
        else if (y <= -60) {

        }
        else if (y <= -50) {

        }
        else if (y <= -40) {

        }
        else if (y <= -30) {

        }
        else if (y <= -20) {

        }
        else if (y <= -10) {

        }
        // Landscape
        else if (y <= 10) {

        }
        else {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}

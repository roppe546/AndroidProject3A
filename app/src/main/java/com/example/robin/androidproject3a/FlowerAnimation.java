package com.example.robin.androidproject3a;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Peonsson on 07/12/15.
 */
public class FlowerAnimation extends android.view.View {

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

    public static void drawFlower(double x) {


        if (x < 1) {

        } else if (x < 2) {

        } else if (x < 3) {

        } else if (x < 4) {

        } else if (x < 5) {

        } else if (x < 6) {

        } else if (x < 7) {

        } else if (x < 8) {

        } else if (x < 9) {

        } else {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}

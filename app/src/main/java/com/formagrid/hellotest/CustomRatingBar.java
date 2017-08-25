package com.formagrid.hellotest;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class CustomRatingBar extends LinearLayout {

    private int mNumStars;
    private List<ImageView> mChildImages;

    private int mFilledColor;
    private int mEmptyColor;

    private int mCurrentValue;

    public CustomRatingBar(Context context, int numStars) {
        super(context);

        mChildImages = new ArrayList<ImageView>();
        mCurrentValue = 0;

        mFilledColor = ContextCompat.getColor(this.getContext(), R.color.green);
        mEmptyColor = ContextCompat.getColor(this.getContext(), R.color.grey);

        this.setOrientation(HORIZONTAL);
        mNumStars = numStars;
        init();
    }

    public void setValue(int value) {
        if (value < 0 || value > mNumStars) {
            throw new RuntimeException("value must be between 0 and maxNumStars inclusive");
        }

        mCurrentValue = value;
        for (int i = 0; i < mNumStars; i += 1) {
            ImageView imageView = mChildImages.get(i);
            if (i + 1 <= mCurrentValue) {
                imageView.getDrawable().mutate().setColorFilter(mFilledColor, PorterDuff.Mode.SRC_IN);
            } else {
                imageView.getDrawable().mutate().setColorFilter(mEmptyColor, PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private void init() {
        for (int i = 0; i < mNumStars; i += 1) {
            ImageView imageView = (ImageView) LayoutInflater.from(this.getContext()).inflate(R.layout.rating_bar_image_view, null);
            imageView.setImageResource(R.drawable.ic_android_black_24dp);

            imageView.setTag(i);
            final int value = i + 1;
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (value == mCurrentValue) {
                        setValue(0);
                    } else {
                        setValue(value);
                    }
                }
            });

            mChildImages.add(imageView);
            this.addView(imageView);
        }
    }

}

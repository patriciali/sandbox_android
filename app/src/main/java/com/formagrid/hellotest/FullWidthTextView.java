package com.formagrid.hellotest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

// stolen from http://stackoverflow.com/a/22901751/665632
public class FullWidthTextView extends TextView {

    public FullWidthTextView(Context context) {
        super(context);
    }

    public FullWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullWidthTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        View parentScrollView = (View) getParent().getParent();

        if (parentScrollView != null) {
            // check the container of the container is an HorizontalScrollView
            if (parentScrollView instanceof HorizontalScrollView) {
                // Yes it is, so change width to HSV's width
                widthMeasureSpec = parentScrollView.getMeasuredWidth();
            }
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

}
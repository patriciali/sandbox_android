package com.formagrid.hellotest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomImageView extends ImageView {

    private Paint mPaint;

    private float mRadius;
    private float mWidth;
    private float mHeight;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.red));
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        // Figure out how big we can make the pie.
        float diameter = Math.min(width, height);

        mWidth = (float) width;
        mHeight = (float) height;
        mRadius = diameter / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mWidth/2, mHeight/2, mRadius, mPaint);

        super.onDraw(canvas);
    }

}

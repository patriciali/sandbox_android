package com.formagrid.hellotest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class OnboardingClickListenerView extends View {

    private Paint mRectPaint;
    private Paint mCirclePaint;

    private ValueAnimator mCircleRadiusAnimator;
    private float mRadius;

    public OnboardingClickListenerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnboardingClickListenerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.orange));

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(ContextCompat.getColor(this.getContext(), R.color.green));

        mCircleRadiusAnimator = new ValueAnimator().ofFloat(50, 150);
        mCircleRadiusAnimator.setDuration(800);
        mCircleRadiusAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mCircleRadiusAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mCircleRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mRadius = value;
                invalidate();
            }
        });

        mCircleRadiusAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.getLayoutParams();
        params.topMargin = 400;
        params.leftMargin = 200;
        this.setLayoutParams(params);

        setMeasuredDimension(800, 800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, 800, 800, mRectPaint);
        canvas.drawCircle(400, 400, mRadius, mCirclePaint);
    }

}

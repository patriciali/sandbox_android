package com.formagrid.hellotest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

// http://www.velir.com/blog/index.php/2010/11/17/android-snapping-horizontal-scroll/
public class RecordCardSnappingHorizontalScrollView extends HorizontalScrollView {

    private static final String TAG = "SnappingScrollView";

    private static final int SWIPE_MIN_DISTANCE = 3;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

    private GestureDetector mGestureDetector;
    private int mActiveFeature;
    private int mFeatureWidth;

    private View mContentView;

    public RecordCardSnappingHorizontalScrollView(Context context) {
        super(context);
        init();
    }

    public RecordCardSnappingHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecordCardSnappingHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mActiveFeature = 0;
        mFeatureWidth = getResources().getDimensionPixelSize(R.dimen.balls);

        // Set the touch listener for handling a slow drag and release
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // if the user swipes
                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mActiveFeature = ((getScrollX() + (mFeatureWidth / 2)) / mFeatureWidth);
                    int scrollTo = mActiveFeature * mFeatureWidth;
                    smoothScrollTo(scrollTo, 0);
                    return true;
                }

                return false;
            }
        });

        // Set the gesture detector for detecting a swipe
        mGestureDetector = new GestureDetector(this.getContext(), new MyGestureDetector(), null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mContentView = findViewById(R.id.content_view);
        mContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActiveFeature == 0) {
                    Log.d("patricia", "onClick");
                } else {
                    mActiveFeature = 0;
                    smoothScrollTo(0, 0);
                }
            }
        });
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private MotionEvent mLastOnDownEvent = null;

        @Override
        public boolean onDown(MotionEvent e) {
            if (e == null) {
                Log.d("patricia", "onDown received null argument");
            } else {
                Log.d("patricia", "ok");
            }
            mLastOnDownEvent = e;
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null) {
                e1 = mLastOnDownEvent;
            }

            if (Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY) {
                return false;
            }

            try {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                    // right to left
                    Log.d("patricia", "right to left " + e1.getX() + " " + e2.getX());
                    mActiveFeature = 1;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
                    // left to right
                    Log.d("patricia", "left to right " + e1.getX() + " " + e2.getX());
                    mActiveFeature = 0;
                }

                smoothScrollTo(mActiveFeature * mFeatureWidth, 0);
                return true;
            } catch (Exception e) {
                Log.e("patricia", "There was an error processing the fling event:" + e.getMessage());
            }

            return false;
        }

    }

}

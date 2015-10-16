package com.formagrid.hellotest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

// http://www.velir.com/blog/index.php/2010/11/17/android-snapping-horizontal-scroll/
public class RecordCardSnappingHorizontalScrollView extends HorizontalScrollView {

    private static final String TAG = "SnappingScrollView";

    private static final int SWIPE_MIN_DISTANCE = 3;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

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

}

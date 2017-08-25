package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class HelloActivity extends Activity {

    private static final int NUM_STARS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        CustomRatingBar ratingBar = new CustomRatingBar(this, NUM_STARS);
        rootLayout.addView(ratingBar);

        ratingBar.setValue(5);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

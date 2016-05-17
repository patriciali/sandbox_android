package com.formagrid.hellotest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class HelloActivity extends Activity {

    private static final String FRAGMENT_TRANSACTION_ID = "first_transaction";

    private FrameLayout mLeftContainer;
    private FrameLayout mRightContainer;
    private FrameLayout mFragmentContainer;

    private boolean mIsFragmentOnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mLeftContainer = (FrameLayout) findViewById(R.id.left_container);
        mRightContainer = (FrameLayout) findViewById(R.id.right_container);
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction firstTransaction = manager.beginTransaction();
        firstTransaction.add(R.id.fragment_container, new FirstFragment(), FRAGMENT_TRANSACTION_ID);
        firstTransaction.commit();

        mIsFragmentOnRight = true;
    }

    public void switchContainer(View view) {
        if (mIsFragmentOnRight) {
            mRightContainer.removeView(mFragmentContainer);
            mLeftContainer.addView(mFragmentContainer);
        } else {
            mLeftContainer.removeView(mFragmentContainer);
            mRightContainer.addView(mFragmentContainer);
        }

        mIsFragmentOnRight = !mIsFragmentOnRight;
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

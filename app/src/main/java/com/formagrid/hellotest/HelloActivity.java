package com.formagrid.hellotest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

public class HelloActivity extends Activity {

    public static final String FIRST_TRANSACTION = "first_transaction";
    public static final String SECOND_TRANSACTION = "second_transaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        FrameLayout left = (FrameLayout) findViewById(R.id.left);
        FrameLayout right = (FrameLayout) findViewById(R.id.right);

        FragmentManager manager = getFragmentManager();

        FragmentTransaction firstTransaction = manager.beginTransaction();
        firstTransaction.add(left.getId(), new FirstFragment(), FIRST_TRANSACTION);
        firstTransaction.commit();

        FragmentTransaction secondTransaction = manager.beginTransaction();
        secondTransaction.add(right.getId(), new SecondFragment(), SECOND_TRANSACTION);
        secondTransaction.commit();
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

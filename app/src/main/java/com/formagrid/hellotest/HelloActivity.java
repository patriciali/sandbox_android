package com.formagrid.hellotest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class HelloActivity extends Activity {

    public static final String FIRST_TRANSACTION = "first_transaction";
    private static final String SECOND_TRANSACTION = "second_transaction";
    private static final String SECOND_TRANSACTION_BACKSTACK = "second_transaction_backstack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction firstTransaction = manager.beginTransaction();
        firstTransaction.add(R.id.left, new FirstFragment(), FIRST_TRANSACTION);
        firstTransaction.commit();
    }

    public void startSecondFragment(int itemPosition) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction secondTransaction = manager.beginTransaction();
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.safeSetArguments(itemPosition);
        secondTransaction.add(R.id.right, secondFragment, SECOND_TRANSACTION);
        secondTransaction.addToBackStack(SECOND_TRANSACTION_BACKSTACK);
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

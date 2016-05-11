package com.formagrid.hellotest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelloFragment extends Fragment {

    public static final String TRANSACTION_BACKSTACK_TAG = "hellofragment_backstack_tag";

    public static void showFragment(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_down);
        HelloFragment fragment = new HelloFragment();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(TRANSACTION_BACKSTACK_TAG);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

}

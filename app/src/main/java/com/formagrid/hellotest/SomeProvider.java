package com.formagrid.hellotest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;

public class SomeProvider {

    public static void showFragment(Activity activity, int containerId) {
        Log.d("patricia", "showing fragment");
        Fragment fragment = new SlidingPanelFragment();
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, "tag");
        transaction.commit();
    }

}

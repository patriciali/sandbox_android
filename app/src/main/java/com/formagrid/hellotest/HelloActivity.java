package com.formagrid.hellotest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HelloActivity extends Activity {

    private static final String EXTRA_AUTOLAUNCH_FRAGMENT = "extra_autolaunch_fragment";

    public static void start(Context context, boolean shouldAutolaunchFragment) {
        Intent intent = new Intent(context, HelloActivity.class);
        intent.putExtra(EXTRA_AUTOLAUNCH_FRAGMENT, shouldAutolaunchFragment);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView launchFragment = (TextView) findViewById(R.id.launch_fragment_text_view);
        launchFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment();
            }
        });

        TextView launchActivity = (TextView) findViewById(R.id.launch_activity_text_view);
        launchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnotherActivity.start(HelloActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == AnotherActivity.FRAGMENT_REQUEST_CODE) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int intResult = extras.getInt(AnotherActivity.EXTRA_INT_EXTRA);
                if (intResult == AnotherActivity.THREE) {
                    showFragment();
                } else {
                    Log.d("patricia", "wrong intResult " + intResult);
                }
            } else {
                Log.d("patricia", "extras == null");
            }
        } else {
            Log.d("patricia", "wrong request code " + requestCode);
        }
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

    private void showFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HelloFragment fragment = new HelloFragment();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

}

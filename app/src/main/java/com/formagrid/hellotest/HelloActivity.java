package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        TextView launchFragment = (TextView) findViewById(R.id.launch_fragment_text_view);
        launchFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloFragment.showFragment(HelloActivity.this);
            }
        });
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

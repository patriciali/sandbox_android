package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClick(View view) {
        SomeProvider.showFragment(this, R.id.some_unique_id);
    }

}

package com.formagrid.hellotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.filepicker.Filepicker;
import io.filepicker.models.FPFile;

public class HelloActivity extends Activity {

    private TextView mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Filepicker.setKey(Secrets.FILEPICKER_DEV_API_KEY);
        Filepicker.setAppName("patricia is the shit");

        mButton = (TextView) findViewById(R.id.tap_me);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelloActivity.this, Filepicker.class);
                startActivityForResult(intent, Filepicker.REQUEST_CODE_GETFILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Filepicker.REQUEST_CODE_GETFILE) {
            if(resultCode == RESULT_OK) {
                Log.d("patricia", "ok");

                // Filepicker always returns array of FPFile objects
                ArrayList<FPFile> fpFiles = data.getParcelableArrayListExtra(Filepicker.FPFILES_EXTRA);

                // Option multiple was not set so only 1 object is expected
                FPFile file = fpFiles.get(0);

                // Do something cool with the result
            } else {
                // Handle errors here
                Log.d("patricia", "!!!!error!!!!");
            }

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

}

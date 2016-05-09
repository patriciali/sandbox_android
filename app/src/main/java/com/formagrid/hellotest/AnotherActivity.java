package com.formagrid.hellotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnotherActivity extends Activity {

    public static final int FRAGMENT_REQUEST_CODE = 55;
    public static final String EXTRA_INT_EXTRA = "extra_int_extra";

    public static final int THREE = 3;

    public static void start(Activity context) {
        Intent intent = new Intent(context, AnotherActivity.class);
        context.startActivityForResult(intent, FRAGMENT_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_INT_EXTRA, THREE);
                setResult(RESULT_OK, intent);

                finish();
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

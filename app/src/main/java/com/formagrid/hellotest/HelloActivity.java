package com.formagrid.hellotest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class HelloActivity extends Activity {

    private Spinner mSpinner;
    private SpinnerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mAdapter = new SpinnerAdapter(this);
        mSpinner.setAdapter(mAdapter);
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

    private class SpinnerAdapter extends ArrayAdapter<String> {

        private String[] mItems;

        public SpinnerAdapter(Context context) {
            super(context, R.layout.spinner_item);

            mItems = new String[]{
                    "one",
                    "two",
                    "three"
            };
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {
            Context context = parent.getContext();

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.text_view);
            textView.setText(mItems[position]);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnotherActivity.start(HelloActivity.this);

                    View root = parent.getRootView();
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                }
            });

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return this.getView(position, convertView, parent);
        }

    }

}

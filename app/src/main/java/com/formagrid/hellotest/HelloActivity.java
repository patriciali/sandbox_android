package com.formagrid.hellotest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    private static class SpinnerAdapter extends ArrayAdapter<String> {

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
            return mItems.length + 1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();

            boolean isSpecial = position == getCount() - 1;
            String tag = isSpecial ? "special" : "normal";
            if (convertView == null || !convertView.getTag().toString().equals(tag)) {
                if (isSpecial) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.spinner_special_item, parent, false);
                    convertView.setTag(tag);
                } else {
                    convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
                    convertView.setTag(tag);
                }
            }

            TextView textView = (TextView) convertView.findViewById(R.id.text_view);
            textView.setText(isSpecial ? "patricia is the shit" : mItems[position]);

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return this.getView(position, convertView, parent);
        }

    }

}

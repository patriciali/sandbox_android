package com.formagrid.hellotest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlidingPanelFragment extends Fragment {

    private final int COUNT = 15;

    private EditText mToolbar;
    private LinearLayout mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.sliding_panel_fragment, container, false);

        mToolbar = (EditText) view.findViewById(R.id.toolbar);

        mListView = (LinearLayout) view.findViewById(R.id.list_view);
        String[] values = getValues();
        for (String value : values) {
            TextView textView = new TextView(view.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(value);
            mListView.addView(textView);
        }

        return view;
    }

    private String[] getValues() {
        String[] values = new String[COUNT];
        for (int i = 0; i < COUNT; i += 1) {
            values[i] = "" + i;
        }
        return values;
    }

}

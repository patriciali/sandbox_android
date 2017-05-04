package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

public class HelloActivity extends Activity {

    private RecyclerView mStacksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mStacksRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mStacksRecyclerView.setHasFixedSize(true);
        mStacksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mStacksRecyclerView.setAdapter(new KanbanAdapter());

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mStacksRecyclerView);
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

package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;

public class HelloActivity extends Activity {

    private RecyclerView mStacksRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mStacksRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mStacksRecyclerView.setHasFixedSize(true);
        mStacksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        KanbanAdapter kanbanAdapter = new KanbanAdapter(new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        mStacksRecyclerView.setAdapter(kanbanAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mStacksRecyclerView);

        ReorderOnlyItemTouchHelperCallback touchHelperCallback = new ReorderOnlyItemTouchHelperCallback(kanbanAdapter, ReorderOnlyItemTouchHelperCallback.HORIZONTAL_LAYOUT_MOVEMENT_FLAGS);
        mItemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(mStacksRecyclerView);
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

package com.formagrid.hellotest;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

public class KanbanDragListener implements View.OnDragListener {

    private boolean mIsDropped = false;

    @Override
    public boolean onDrag(View target, DragEvent dragEvent) {
        View source = (View) dragEvent.getLocalState();
        RecyclerView sourceParent = (RecyclerView) source.getParent();
        int sourcePosition = (int) source.getTag();

        RecyclerView targetParent = (RecyclerView) target.getParent();
        int targetPosition = (int) target.getTag();

        KanbanStackAdapter sourceParentAdapter = (KanbanStackAdapter) sourceParent.getAdapter();
        KanbanStackAdapter targetParentAdapter = (KanbanStackAdapter) targetParent.getAdapter();

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                int stackIndex = (int) targetParent.getTag();
                RecyclerView container = (RecyclerView) targetParent.getParent();
                container.smoothScrollToPosition(stackIndex);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DROP:
                mIsDropped = true;

                if (sourceParentAdapter == targetParentAdapter) {
                    targetParentAdapter.onItemMove(sourcePosition, targetPosition);
                } else {
                    Row row = sourceParentAdapter.onItemDismiss(sourcePosition);
                    targetParentAdapter.onItemAdded(targetPosition, row);
                }

                target.setVisibility(View.VISIBLE);
                break;
        }

        if (!mIsDropped) {
            source.setVisibility(View.VISIBLE);
        }

        return true;
    }

}

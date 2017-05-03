package com.formagrid.hellotest;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

public class KanbanDragListener implements View.OnDragListener {

    private boolean mIsDropped = false;

    @Override
    public boolean onDrag(View target, DragEvent dragEvent) {
        View sourceChild = (View) dragEvent.getLocalState();

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                mIsDropped = true;

                RecyclerView sourceParent = (RecyclerView) sourceChild.getParent();
                int sourcePosition = (int) sourceChild.getTag();

                RecyclerView targetParent = (RecyclerView) target.getParent();
                int targetPosition = (int) target.getTag();

                KanbanStackAdapter sourceParentAdapter = (KanbanStackAdapter) sourceParent.getAdapter();
                KanbanStackAdapter targetParentAdapter = (KanbanStackAdapter) targetParent.getAdapter();

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
            sourceChild.setVisibility(View.VISIBLE);
        }

        return true;
    }

}

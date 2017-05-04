package com.formagrid.hellotest;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewParent;

public class KanbanDragListener implements View.OnDragListener {

    private boolean mIsDropped = false;

    @Override
    public boolean onDrag(View target, DragEvent dragEvent) {
        View source = (View) dragEvent.getLocalState();
        RecyclerView sourceParent = (RecyclerView) source.getParent();
        int sourcePosition = ((KanbanStackAdapter.ViewHolder) source.getTag()).getAdapterPosition();

        ViewParent tempTargetParent = target.getParent();
        if (!(tempTargetParent instanceof RecyclerView)) {
            // no-op if the parent isn't instance of RecyclerView
            return true;
        }
        RecyclerView targetParent = (RecyclerView) target.getParent();
        int targetPosition = ((KanbanStackAdapter.ViewHolder) target.getTag()).getAdapterPosition();

        KanbanStackAdapter sourceParentAdapter = (KanbanStackAdapter) sourceParent.getAdapter();
        KanbanStackAdapter targetParentAdapter = (KanbanStackAdapter) targetParent.getAdapter();

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                int stackIndex = ((KanbanAdapter.ViewHolder) targetParent.getTag()).getAdapterPosition();
                RecyclerView container = (RecyclerView) targetParent.getParent().getParent(); // TODO HACK
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

package com.formagrid.hellotest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ReorderOnlyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final int VERTICAL_LAYOUT_MOVEMENT_FLAGS =
            makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.START | ItemTouchHelper.END);

    public static final int HORIZONTAL_LAYOUT_MOVEMENT_FLAGS =
            makeMovementFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                    ItemTouchHelper.START | ItemTouchHelper.END);

    public static final int FLOW_LAYOUT_MOVEMENT_FLAGS =
            makeMovementFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.START | ItemTouchHelper.END);

    private static final boolean IS_SWIPE_ENABLED = false;

    private static final int DEFAULT_SAVED_POSITION = -1;

    private int mFromPosition = DEFAULT_SAVED_POSITION;
    private int mToPosition = DEFAULT_SAVED_POSITION;

    public interface ReorderOnlyItemTouchHelperAdapter {

        /**
         * this is called every time an item is moved a single position. the adapter should respond
         * to this by updating the UI.
         */
        void onItemMove(int fromPosition, int toPosition);

        /**
         * this is called when the user has released the item. the adapter should sync the changes.
         */
        void onItemDrop(int fromPosition, int toPosition);

    }

    private ReorderOnlyItemTouchHelperAdapter mAdapter;
    private boolean mIsEditable;

    private int mMovementFlags;

    public ReorderOnlyItemTouchHelperCallback(ReorderOnlyItemTouchHelperAdapter adapter, int movementFlags) {
        this.mAdapter = adapter;
        this.mMovementFlags = movementFlags;
    }

    public void setEditable(boolean isEditable) {
        mIsEditable = isEditable;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mIsEditable;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return IS_SWIPE_ENABLED;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return mMovementFlags;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // update mFromPosition if it's the first time, and always update mToPosition.
        if (mFromPosition == DEFAULT_SAVED_POSITION) {
            mFromPosition = viewHolder.getAdapterPosition();
        }
        mToPosition = target.getAdapterPosition();

        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    // stolen from https://github.com/iPaulPro/Android-ItemTouchHelper-Demo/issues/11#issuecomment-135858015
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (mFromPosition != DEFAULT_SAVED_POSITION && mToPosition != DEFAULT_SAVED_POSITION) {
            mAdapter.onItemDrop(mFromPosition, mToPosition);
        }

        // clear saved positions
        mFromPosition = DEFAULT_SAVED_POSITION;
        mToPosition = DEFAULT_SAVED_POSITION;

        // https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-6a6f0c422efd#.tbbcib5dt
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // This ItemTouchHelper.Callback only handles move events, so we ignore onSwiped events
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

}
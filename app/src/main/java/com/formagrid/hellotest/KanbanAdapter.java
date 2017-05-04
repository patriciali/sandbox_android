package com.formagrid.hellotest;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KanbanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ReorderOnlyItemTouchHelperCallback.ReorderOnlyItemTouchHelperAdapter {

    private static final int TYPE_STACK = 0;
    private static final int TYPE_ADD_STACK = 1;

    private OnStartDragListener mOnStartDragListener;

    private List<String> mStackTitles;
    private List<KanbanStackAdapter> mAdapterList;

    public KanbanAdapter(OnStartDragListener onStartDragListener) {
        super();
        mOnStartDragListener = onStartDragListener;

        mStackTitles = new ArrayList<String>();
        mStackTitles.add("Diving In");
        mStackTitles.add("Mastering Trello");
        mStackTitles.add("Getting Started");
        mStackTitles.add("More Info");

        mAdapterList = new ArrayList<KanbanStackAdapter>();
        for (int i = 0; i < mStackTitles.size(); i += 1) {
            List<Row> modelItems = new ArrayList<Row>();
            modelItems.add(new Row("a" + (i+1)));
            modelItems.add(new Row("b" + (i+1)));
            modelItems.add(new Row("c" + (i+1)));
            modelItems.add(new Row("d" + (i+1)));
            modelItems.add(new Row("e" + (i+1)));

            mAdapterList.add(new KanbanStackAdapter(modelItems));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_STACK) {
            RelativeLayout kanbanStack = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.kanban_stack, parent, false);
            return new KanbanStackViewHolder(kanbanStack);
        } else {
            TextView addStackCard = (TextView) LayoutInflater.from(context).inflate(R.layout.kanban_add_stack, parent, false);
            return new AddStackViewHolder(addStackCard);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder untypedHolder, int position) {
        int viewType = this.getItemViewType(position);
        if (viewType == TYPE_STACK) {
            KanbanStackViewHolder holder = (KanbanStackViewHolder) untypedHolder;
            holder.title.setText(mStackTitles.get(position));

            holder.kanbanStackRecyclerView.setAdapter(mAdapterList.get(position));
            holder.kanbanStackRecyclerView.setTag(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mStackTitles.size()) {
            return TYPE_ADD_STACK;
        }
        return TYPE_STACK;
    }

    @Override
    public int getItemCount() {
        return mStackTitles.size() + 1;
    }

    /**
     * ReorderOnlyItemTouchHelperCallback.ReorderOnlyItemTouchHelperAdapter
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        // we don't allow dragging to the "add stack" position
        if (toPosition == mStackTitles.size()) {
            return;
        }

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                // TODO we should put mStackTitles and mAdapterList in one list so that we don't have to do this shit
                Collections.swap(mStackTitles, i, i + 1);
                Collections.swap(mAdapterList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mStackTitles, i, i - 1);
                Collections.swap(mAdapterList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDrop(int fromPosition, int toPosition) {
        // sync to server
    }

    public class KanbanStackViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public RecyclerView kanbanStackRecyclerView;
        public TextView addCard;

        public KanbanStackViewHolder(RelativeLayout kanbanStack) {
            super(kanbanStack);
            kanbanStackRecyclerView = (RecyclerView) kanbanStack.findViewById(R.id.recycler_view);
            kanbanStackRecyclerView.setHasFixedSize(true);
            kanbanStackRecyclerView.setLayoutManager(new LinearLayoutManager(kanbanStack.getContext(), LinearLayoutManager.VERTICAL, false));

            title = (TextView) kanbanStack.findViewById(R.id.title);
            title.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) ==
                            MotionEvent.ACTION_DOWN) {
                        mOnStartDragListener.onStartDrag(KanbanStackViewHolder.this);
                    }
                    return false;
                }
            });

            addCard = (TextView) kanbanStack.findViewById(R.id.add_card);
            addCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KanbanStackAdapter adapter = mAdapterList.get(KanbanStackViewHolder.this.getAdapterPosition());
                    kanbanStackRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    adapter.addCardToEnd();
                    kanbanStackRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            });
        }

    }

    public class AddStackViewHolder extends RecyclerView.ViewHolder {

        public AddStackViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mStackTitles.add("patricia is the shit");
                    mAdapterList.add(new KanbanStackAdapter(new ArrayList<Row>()));
                    KanbanAdapter.this.notifyItemInserted(mStackTitles.size() - 1);
                }
            });
        }

    }

}

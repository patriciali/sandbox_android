package com.formagrid.hellotest;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KanbanStackAdapter extends RecyclerView.Adapter<KanbanStackAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(CardView view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }

    }

    private List<Row> mModel;

    public KanbanStackAdapter(List<Row> model) {
        super();
        mModel = new ArrayList<Row>();
        mModel.addAll(model);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.kanban_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mModel.get(position).value);

        holder.itemView.setTag(holder);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                } else {
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
            }
        });
        holder.itemView.setOnDragListener(new KanbanDragListener());
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public void addCardToEnd() {
        mModel.add(new Row("patricia is the shit"));
        notifyItemInserted(mModel.size() - 1);
    }

    // KanbanDragListener methods. TODO should probably be behind an interface
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mModel, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mModel, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public Row onItemDismiss(int position) {
        Row row = mModel.get(position);
        mModel.remove(position);
        notifyItemRemoved(position);
        return row;
    }

    public void onItemAdded(int position, Row row) {
        mModel.add(position, row);
        notifyItemInserted(position);
    }

}

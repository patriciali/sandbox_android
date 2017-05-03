package com.formagrid.hellotest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KanbanStackAdapter extends RecyclerView.Adapter<KanbanStackAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(CardView view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }

    }

    private List<String> mModel;

    public KanbanStackAdapter(List<String> model) {
        super();
        mModel = new ArrayList<String>();
        mModel.addAll(model);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.kanban_stack_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mModel.get(position));
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

}

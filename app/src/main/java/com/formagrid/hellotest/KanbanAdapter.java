package com.formagrid.hellotest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class KanbanAdapter extends RecyclerView.Adapter<KanbanAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView kanbanStackRecyclerView;

        public ViewHolder(RecyclerView recyclerView) {
            super(recyclerView);
            kanbanStackRecyclerView = recyclerView;
        }

    }

    private static final int NUM_STACKS = 8;
    private List<KanbanStackAdapter> mAdapterList;

    public KanbanAdapter() {
        super();
        mAdapterList = new ArrayList<KanbanStackAdapter>();
        for (int i = 0; i < NUM_STACKS; i += 1) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        RecyclerView kanbanStackRecyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.kanban_stack_recyclerview, parent, false);
        kanbanStackRecyclerView.setHasFixedSize(true);
        kanbanStackRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        return new ViewHolder(kanbanStackRecyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.kanbanStackRecyclerView.setAdapter(mAdapterList.get(position));
        holder.kanbanStackRecyclerView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return NUM_STACKS;
    }

}

package com.formagrid.hellotest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KanbanAdapter extends RecyclerView.Adapter<KanbanAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public RecyclerView kanbanStackRecyclerView;
        public TextView addCard;

        public ViewHolder(RelativeLayout kanbanStack) {
            super(kanbanStack);
            kanbanStackRecyclerView = (RecyclerView) kanbanStack.findViewById(R.id.recycler_view);
            kanbanStackRecyclerView.setHasFixedSize(true);
            kanbanStackRecyclerView.setLayoutManager(new LinearLayoutManager(kanbanStack.getContext(), LinearLayoutManager.VERTICAL, false));

            title = (TextView) kanbanStack.findViewById(R.id.title);
            addCard = (TextView) kanbanStack.findViewById(R.id.add_card);
            addCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KanbanStackAdapter adapter = mAdapterList.get(ViewHolder.this.getAdapterPosition());
                    adapter.addCardToEnd();
                    kanbanStackRecyclerView.scrollToPosition(adapter.getItemCount());
                }
            });
        }

    }

    private static final String[] STACK_TITLES = new String[]{
            "Mastering Trello",
            "Diving In",
            "Getting Started",
            "More Info",
    };
    private List<KanbanStackAdapter> mAdapterList;

    public KanbanAdapter() {
        super();
        mAdapterList = new ArrayList<KanbanStackAdapter>();
        for (int i = 0; i < STACK_TITLES.length; i += 1) {
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
        RelativeLayout kanbanStack = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.kanban_stack, parent, false);
        return new ViewHolder(kanbanStack);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(STACK_TITLES[position]);

        holder.kanbanStackRecyclerView.setAdapter(mAdapterList.get(position));
        holder.kanbanStackRecyclerView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return STACK_TITLES.length;
    }

}

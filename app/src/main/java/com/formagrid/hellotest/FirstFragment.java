package com.formagrid.hellotest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private FirstAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout topLevelView = (FrameLayout) inflater.inflate(R.layout.fragment_first, container, false);

        RecyclerView recyclerView = (RecyclerView) topLevelView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new FirstAdapter();
        recyclerView.setAdapter(mAdapter);

        return topLevelView;
    }

    private class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(TextView view) {
                super(view);

                textView = view;
            }

        }

        private String[] mItems;

        public FirstAdapter() {
            mItems = Constants.STRINGS.clone();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.textView.setText(mItems[position]);

            final int itemPosition = holder.getAdapterPosition();
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems[itemPosition] = "Roosevelt";
                    mAdapter.notifyItemChanged(itemPosition);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.length;
        }

    }

}

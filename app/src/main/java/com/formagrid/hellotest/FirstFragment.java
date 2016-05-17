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

import java.util.ArrayList;
import java.util.List;

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

    public StringWrapper getItemAtIndex(int index) {
        return mAdapter.getItemAtIndex(index);
    }

    public void notifyItemAtIndexChanged(int index) {
        mAdapter.notifyItemChanged(index);
    }

    private class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(TextView view) {
                super(view);

                textView = view;
            }

        }

        private List<StringWrapper> mItems;

        public FirstAdapter() {
            mItems = new ArrayList<StringWrapper>();
            for (String string : Constants.STRINGS) {
                mItems.add(new StringWrapper(string));
            }
        }

        public StringWrapper getItemAtIndex(int index) {
            return mItems.get(index);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.textView.setText(mItems.get(position).string);

            final int itemPosition = holder.getAdapterPosition();
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HelloActivity activity = (HelloActivity) getActivity();
                    activity.startSecondFragment(itemPosition);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

    }

}

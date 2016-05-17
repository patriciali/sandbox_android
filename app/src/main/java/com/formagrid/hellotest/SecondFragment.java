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

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout topLevelView = (FrameLayout) inflater.inflate(R.layout.fragment_hello, container, false);

        RecyclerView recyclerView = (RecyclerView) topLevelView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SecondAdapter adapter = new SecondAdapter();
        recyclerView.setAdapter(adapter);

        return topLevelView;
    }

    private class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(TextView view) {
                super(view);

                textView = view;
            }

        }

        private String[] mItems;

        public SecondAdapter() {
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

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirstFragment fragment = (FirstFragment) getActivity().getFragmentManager().findFragmentByTag(HelloActivity.FIRST_TRANSACTION);
                    int index = holder.getAdapterPosition();
                    fragment.setStringAtIndex(index, "Trump");
                    fragment.notifyItemAtIndexChanged(index);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.length;
        }

    }

}

package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class HelloActivity extends Activity {

    private RelativeLayout mRootLayout;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mRootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        mRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("patricia", "mRootLayout click");
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(Arrays.asList("hi", "this", "is", "some", "text"));
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("patricia", "mRecyclerView click");
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

        public class Holder extends RecyclerView.ViewHolder {

            protected TextView textView;

            public Holder(TextView itemView) {
                super(itemView);
                this.textView = itemView;
            }

        }

        private List<String> contents;

        public RecyclerAdapter(List<String> contents) {
            this.contents = contents;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            return new Holder(textView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(contents.get(position));
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }

    }

}

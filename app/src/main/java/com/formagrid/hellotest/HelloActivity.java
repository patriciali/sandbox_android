package com.formagrid.hellotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HelloActivity extends Activity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new HelloAdapter());
        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        mRecyclerView.setItemAnimator(animator);
    }

    private static class HelloAdapter extends RecyclerView.Adapter<HelloAdapter.HelloViewHolder> {

        public class HelloViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public HelloViewHolder(CardView cardView) {
                super(cardView);
                textView = (TextView) cardView.findViewById(R.id.text_view);
            }

        }

        @Override
        public HelloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.card_item, parent, false);
            return new HelloViewHolder(cardView);
        }

        @Override
        public void onBindViewHolder(HelloViewHolder holder, int position) {
            bind(holder);
        }

        @Override
        public void onBindViewHolder(HelloViewHolder holder, int position, List<Object> payload) {
            Log.d("fuck", "payload " + payload.toString());
            bind(holder);
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        private void bind(final HelloViewHolder holder) {
            holder.textView.setText("item " + holder.getAdapterPosition());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = holder.getAdapterPosition();
                    Log.d("fuck", "click " + position);
                    HelloAdapter.this.notifyItemChanged(position, "payload " + position);
                }
            });
        }

    }

}

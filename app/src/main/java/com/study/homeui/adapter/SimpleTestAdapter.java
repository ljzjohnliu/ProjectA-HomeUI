package com.study.homeui.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTestAdapter extends RecyclerView.Adapter<SimpleTestAdapter.TextViewHolder> {

    private String TAG = "SimpleTestAdapter";
    ArrayList<String> data;

    public SimpleTestAdapter() {

    }

    public SimpleTestAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));

        String val = data == null ? "TextView " + position : data.get(position);
        tv.setText(val);
        Log.d(TAG, "onBindViewHolder " + position);
    }


    @Override
    public int getItemCount() {
        return data == null ? 300 : data.size();
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }

}

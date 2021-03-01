package com.study.homeui.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected T itemData;
    protected ViewGroup parentView;
    protected View view;

    public BaseViewHolder(@NonNull View view) {
        super(view);
    }

    public BaseViewHolder(ViewGroup parent, int layoutResId) {
        this(parent, LayoutInflater.from(parent.getContext())
                .inflate(layoutResId, parent, false));
        view = LayoutInflater.from(parent.getContext())
                .inflate(layoutResId, parent, false);
    }

    public BaseViewHolder(ViewGroup parent, View itemView) {
        super(itemView);
        parentView = parent;
    }

    protected abstract void onBindViewHolder(T item);

    public void bindViewHolder(T item) {
        this.itemData = item;
        onBindViewHolder(item);
    }

    public T getItemData() {
        return this.itemData;
    }
}

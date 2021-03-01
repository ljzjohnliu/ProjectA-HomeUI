package com.study.homeui.adapter.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.study.homeui.widget.BaseViewHolder;

import butterknife.ButterKnife;

public abstract class BaseHomeViewHolder<T extends IHomeInterface> extends BaseViewHolder<IHomeInterface> {

    public BaseHomeViewHolder(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public BaseHomeViewHolder(ViewGroup parent, int layoutResId) {
        super(parent, layoutResId);
        ButterKnife.bind(this, view);
    }

    public BaseHomeViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindViewHolder(IHomeInterface item) {
        super.bindViewHolder(item);
    }

    protected abstract int getResId();

    protected void onLongClick() {

    }

    protected void onViewHolderAttached() {

    }

    protected void onViewHolderDetached() {

    }

}

package com.study.homeui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.homeui.R;
import com.study.homeui.adapter.home.BaseHomeViewHolder;
import com.study.homeui.adapter.home.ContentViewHolder;
import com.study.homeui.adapter.home.IHomeInterface;
import com.study.homeui.adapter.home.TitleViewHolder;
import com.study.homeui.bean.HomeTitleItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewWorldAdapter extends RecyclerView.Adapter<BaseHomeViewHolder> {
    private static final String TAG = "NewWorldAdapter";

    public final static int VIEW_TYPE_HEAD = 0;
    public final static int VIEW_TYPE_CONTENT = 1;

    private List<IHomeInterface> mData;

    private NewWorldAdapter.OnItemClickListener onItemClickListener;

    View.OnClickListener ivGoToChildClickListener;

    public NewWorldAdapter(List<IHomeInterface> data) {
        this.mData = data;
    }

    public List<IHomeInterface> getData() {
        return mData;
    }

    public void updateData(List<IHomeInterface> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addNewItem() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
//        mData.add(0, "new Item");
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(0);
        notifyItemRemoved(0);
    }

    public void deleteItem(int pos) {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(pos);
        notifyItemRemoved(((pos - 1) > 0) ? (pos - 1) : 0);
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(NewWorldAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setIvGoToChildClickListener(View.OnClickListener listener) {
        this.ivGoToChildClickListener = listener;
    }

    @NonNull
    @Override
    public BaseHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder get called, viewType = " + viewType);
        switch (viewType) {
            case VIEW_TYPE_HEAD:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.world_title_layout, parent, false));
//                return new TitleViewHolder(parent, R.layout.world_title_layout);
            case VIEW_TYPE_CONTENT:
//                return new ContentViewHolder(parent, R.layout.world_item_layout);
                return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.world_item_layout, parent, false));
            default:
                return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.world_item_layout, parent, false));
        }
    }

    List<OpterCity> citys = new ArrayList<>();

    public static class OpterCity {
        int position;
        boolean isShowAll;
        List<Integer> optList;
    }

    @Override
    public void onBindViewHolder(final BaseHomeViewHolder holder, int position) {
        holder.bindViewHolder(mData.get(position));

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            }
        });

        if (mData.get(position).getType() == NewWorldAdapter.VIEW_TYPE_HEAD) {
            boolean isChecked = ((TitleViewHolder) holder).ivGoToChildLv.isChecked();
            int number = ((HomeTitleItem) mData.get(position)).getClubNum();
            if (number > 3) {
                ((TitleViewHolder) holder).ivGoToChildLv.setVisibility(View.VISIBLE);
                OpterCity opterList = new OpterCity();
                opterList.position = position;
                opterList.isShowAll = isChecked;
                List<Integer> integerList = new LinkedList<>();
                for (int i = 4; i <= number; i++) {
                    integerList.add(position + i);
                }
                opterList.optList = integerList;
                citys.add(opterList);
            } else {
                ((TitleViewHolder) holder).ivGoToChildLv.setVisibility(View.GONE);
            }

            //图标的点击事件
            ((TitleViewHolder) holder).ivGoToChildLv.setOnClickListener(view -> {
                if (ivGoToChildClickListener != null) {
                    ivGoToChildClickListener.onClick(view);
                }
                if (((TitleViewHolder) holder).ivGoToChildLv.isChecked()) {
                    for (OpterCity city : citys) {
                        if (city.position == position) {
                            city.isShowAll = true;
                        }
                    }
                    notifyDataSetChanged();
                } else {
                    for (OpterCity city : citys) {
                        if (city.position == position) {
                            city.isShowAll = false;
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

        setVisibility(true, holder.itemView);
        for (OpterCity city : citys) {
            if (city.optList != null && city.optList.size() > 0) {
                for (int i = 0; i < city.optList.size(); i++) {
                    if (position == city.optList.get(i)) {
                        setVisibility(city.isShowAll, holder.itemView);
                    }
                }
            }
        }
    }

    //防止隐藏item出现空白
    public void setVisibility(boolean isVisible, View itemView) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.height = RelativeLayout.LayoutParams.WRAP_CONTENT;// 这里注意使用自己布局的根布局类型
            param.width = RelativeLayout.LayoutParams.MATCH_PARENT;// 这里注意使用自己布局的根布局类型
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        IHomeInterface worldItem = mData.get(position);
        int worldItemType = worldItem.getType();
        return worldItemType;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}

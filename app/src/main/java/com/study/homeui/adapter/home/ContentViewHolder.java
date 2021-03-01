package com.study.homeui.adapter.home;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.study.homeui.R;
import com.study.homeui.adapter.NewWorldAdapter;
import com.study.homeui.bean.HomeContentItem;
import com.study.homeui.widget.CustomModel;
import com.study.homeui.widget.GroupAvatarView;
import com.sunfusheng.marqueeview.MarqueeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentViewHolder extends BaseHomeViewHolder<HomeContentItem> {

    @BindView(R.id.rv_title)
    TextView mTitle;
    @BindView(R.id.hong_bao_num)
    TextView mHongBaoNum;
    @BindView(R.id.group_avatar)
    GroupAvatarView groupAvatarView;
    @BindView(R.id.rv_des)
    TextView mDes;
    @BindView(R.id.marqueeview)
    MarqueeView marqueeView;
    @BindView(R.id.club_status_img)
    SimpleDraweeView clubStatusImg;
    @BindView(R.id.club_cover_img)
    SimpleDraweeView clubCoverImg;
    @BindView(R.id.club_cover_layout)
    RelativeLayout clubCoverLy;

    public ContentViewHolder(View itemView) {
        super(itemView);
    }

    public ContentViewHolder(ViewGroup parent, int layoutResId) {
        super(parent, layoutResId);
        ButterKnife.bind(this, view);
    }

    public ContentViewHolder(ViewGroup parent, View itemView) {
        super(parent, itemView);
    }

    @Override
    protected void onBindViewHolder(IHomeInterface item) {
        Log.d("ljz", "ContentViewHolder, onBindViewHolder: getType = " + item.getType());
        if (item.getType() == NewWorldAdapter.VIEW_TYPE_CONTENT) {
            HomeContentItem homeNewClubItemInfo = (HomeContentItem) item;

            this.mTitle.setText(homeNewClubItemInfo.getTitle());

            // 绑定数据
            this.mTitle.setText(homeNewClubItemInfo.getTitle());

            if (homeNewClubItemInfo.getHongbaoNum() > 0) {
                this.mHongBaoNum.setText(homeNewClubItemInfo.getHongbaoNum());
                this.mHongBaoNum.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(homeNewClubItemInfo.getCoverImg())) {
                this.clubCoverImg.setImageURI(homeNewClubItemInfo.getCoverImg());
                this.clubCoverLy.setVisibility(View.VISIBLE);
                this.clubStatusImg.setVisibility(View.GONE);
            }

            //用户入群状态 0:未入群 1:已入群 2:审核中
            switch (homeNewClubItemInfo.getUserClubStatus()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }

            this.mDes.setText(homeNewClubItemInfo.getDes());
            this.marqueeView.startWithList(homeNewClubItemInfo.getModels());
            this.groupAvatarView.initDatas(homeNewClubItemInfo.getAvatarList(), homeNewClubItemInfo.getTotalNum());

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
//                    if (onItemClickListener != null) {
//                        int pos = holder.getLayoutPosition();
//                        onItemClickListener.onItemClick(holder.itemView, pos);
//                    }
                }
            });

            this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    if (onItemClickListener != null) {
//                        int pos = holder.getLayoutPosition();
//                        onItemClickListener.onItemLongClick(holder.itemView, pos);
//                    }
                    //表示此事件已经消费，不会触发单击事件
                    return true;
                }
            });
        }
    }

    @Override
    protected int getResId() {
        return R.layout.world_item_layout;
    }
}
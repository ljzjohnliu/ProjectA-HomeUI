package com.study.homeui.adapter.home;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.study.homeui.R;
import com.study.homeui.adapter.NewWorldAdapter;
import com.study.homeui.bean.HomeTitleItem;

public class TitleViewHolder extends BaseHomeViewHolder<HomeTitleItem> {

    ImageView ivLocationTitle;
    public ToggleButton ivGoToChildLv;

    public TitleViewHolder(View itemView) {
        super(itemView);
        ivLocationTitle = itemView.findViewById(R.id.location_title);
        ivGoToChildLv = itemView.findViewById(R.id.iv_goToChildLV);
    }

    @Override
    protected void onBindViewHolder(IHomeInterface item) {
        Log.d("ljz", "TitleViewHolder, onBindViewHolder: getType = " + item.getType());
        if (item.getType() == NewWorldAdapter.VIEW_TYPE_HEAD) {
            HomeTitleItem homeCityItem = (HomeTitleItem) item;
            Log.d("ljz", "TitleViewHolder, onBindViewHolder:getCityImg = " + homeCityItem.getCityImg());
            if (homeCityItem.getCityImg() != null) {
                this.ivLocationTitle.setImageURI(Uri.parse(homeCityItem.getCityImg()));
            }

            //图标的点击事件
            this.ivGoToChildLv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (ivGoToChildClickListener != null) {
//                        ivGoToChildClickListener.onClick(view);
//                    }
//                    Log.d("ljz", "onClick: isChecked = " + groupHold.ivGoToChildLv.isChecked());
//                    if (groupHold.ivGoToChildLv.isChecked()) {
//                        groupHold.show3ItemRv.setVisibility(View.GONE);
//                    } else {
//                        groupHold.show3ItemRv.setVisibility(View.VISIBLE);
//                    }
                }
            });
        }
    }

    @Override
    protected int getResId() {
        return R.layout.world_title_layout;
    }
}
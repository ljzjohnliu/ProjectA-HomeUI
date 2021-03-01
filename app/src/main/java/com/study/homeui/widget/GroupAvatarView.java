package com.study.homeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.study.homeui.R;
import com.study.homeui.utils.DensityUtil;

import java.util.List;

public class GroupAvatarView extends ViewGroup {
    /**
     * 头像的半径
     */
    private int mRadius;
    /**
     * 头像间的距离
     */
    private float mSpace;
    private Context mContext;
    private LayoutInflater mInflater;
    /**
     * 最大头像数目
     */
    private int mMaxCount;
    /**
     * 当前移动的偏移量
     */
    private int mCurrentOffset;
    /**
     * 是否显示动画效果
     */
    private boolean mIsShowFrame;
    private int mFrameColor;

    private OnClickListener clickListener;
    private TextView avatNum;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        this.clickListener = l;
    }

    public GroupAvatarView(Context context) {
        this(context, null);
    }

    public GroupAvatarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GroupAvatarView);
        if (array != null) {
            int radius = array.getInteger(R.styleable.GroupAvatarView_radius, 13);
            mSpace = array.getFloat(R.styleable.GroupAvatarView_space, (float) 0.5);
            mMaxCount = array.getInteger(R.styleable.GroupAvatarView_maxCount, 6);
            mIsShowFrame = array.getBoolean(R.styleable.GroupAvatarView_isShowFrame, true);
            mFrameColor = array.getColor(R.styleable.GroupAvatarView_frameColor, Color.RED);

            mRadius = DensityUtil.dip2px(context, radius);

            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heiMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heiMode = MeasureSpec.getMode(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int widMeasure = MeasureSpec.getSize(widthMeasureSpec);

        int wid = 0;
        int hei = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            lp.width = 2 * mRadius;
            lp.height = lp.width;
            child.setLayoutParams(lp);
            // 测量子View的宽和高,系统提供的measureChild
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 子View占据的宽度
            int childWidth = child.getMeasuredWidth();
            // 子View占据的高度
            int childHeight = child.getMeasuredHeight();

            if (i < mMaxCount) {
                if (i == 0) {
                    wid = wid + childWidth;
                } else {
                    wid = (int) (wid + childWidth * mSpace);
                }
            }
            hei = Math.max(hei, childHeight);
        }
        //如果是exactly使用测量宽和高，否则使用自己设置的宽和高
        setMeasuredDimension((widMode == MeasureSpec.EXACTLY) ? widMeasure : wid,
                (heiMode == MeasureSpec.EXACTLY) ? heiMeasure : hei);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        int left = -mCurrentOffset;
        int top = 0;
        int right = -mCurrentOffset;
        for (int i = 0; i < count; i++) {
            View child;
            child = getChildAt(count - i - 1);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (i == 0) {
                right = right + childWidth;
            } else {
                right = (int) (right + childWidth * mSpace);
            }
            child.layout(left, top, right, childHeight);
            left = (int) (left + childWidth * mSpace);
        }

    }

    /**
     * 初始化数据
     *
     * @param list
     */
    public void initDatas(List<String> list, int totalNum) {
        if (list == null) {
            return;
        }
        removeAllViews();
        int size = list.size();
        mMaxCount = size;
        avatNum = (TextView) mInflater.inflate(R.layout.avatar_num, this, false);
        avatNum.setText(String.valueOf(totalNum));
        this.addView(avatNum);
        for (int i = 0; i < size - 1; i++) {
            SimpleDraweeView iv = (SimpleDraweeView) mInflater.inflate(R.layout.avatar, this, false);
            RoundingParams rp = new RoundingParams();
            if (mIsShowFrame) {
                rp.setBorder(mFrameColor, 6);
            }
            rp.setRoundAsCircle(true);
            GenericDraweeHierarchy build = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                    .setRoundingParams(rp)
                    .build();
            iv.setHierarchy(build);
            iv.setImageURI(list.get(size - i - 1));
            if (clickListener != null) {
                iv.setOnClickListener(clickListener);
            }
            this.addView(iv);
        }
    }

    /**
     * 添加头像，没有动画监听
     *
     * @param ava
     */
    public void addData(String ava) {
        if (mMaxCount <= 0) {
            return;
        }
        if (TextUtils.isEmpty(ava)) {
            return;
        }
        int childCount = getChildCount();
        final SimpleDraweeView iv = (SimpleDraweeView) mInflater.inflate(R.layout.avatar, this, false);
        RoundingParams rp = new RoundingParams();
        if (mIsShowFrame) {
            rp.setBorder(mFrameColor, 6);
        }
        rp.setRoundAsCircle(true);
        GenericDraweeHierarchy build = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .setRoundingParams(rp)
                .build();
        iv.setHierarchy(build);
        iv.setImageURI(ava);
        this.addView(iv, 0);

        if (childCount >= mMaxCount) {
            mCurrentOffset = 0;
            int count = getChildCount();
            removeViewAt(count - 1);
        }
    }

    /**
     * 设置最大头像数
     *
     * @param count
     */
    public void setMaxCount(int count) {
        this.mMaxCount = count;
        int childCount = getChildCount();
        if (childCount > mMaxCount) {
            for (int i = 0; i < childCount - mMaxCount; i++) {
                int currentCount = getChildCount();
                removeViewAt(currentCount - 1);
            }
        }
    }

}

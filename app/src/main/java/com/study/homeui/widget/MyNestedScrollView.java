package com.study.homeui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.widget.NestedScrollView;

import com.study.homeui.R;

public class MyNestedScrollView extends NestedScrollView {
    private String TAG = "MyNestedScrollView";

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 监控recyclerView_head显示区域，设置是否透传事件给下层的view
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        boolean result = super.onTouchEvent(e);
        View headView = findViewById(R.id.recyclerView_head);
        Rect globalRect = new Rect();
        headView.getGlobalVisibleRect(globalRect);
        if (globalRect.bottom <= 0) {
            return super.onTouchEvent(e);
        } else {
            return false;
        }
    }
}

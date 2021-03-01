package com.study.homeui.widget;

import com.sunfusheng.marqueeview.IMarqueeItem;

public class CustomModel implements IMarqueeItem {

    public int id;
    public String chat1;
    public String chat2;

    public CustomModel(int id, String chat1, String chat2) {
        this.id = id;
        this.chat1 = chat1;
        this.chat2 = chat2;
    }

    @Override
    public CharSequence marqueeMessage() {
        return chat1 + "\n" + chat2;
    }
}

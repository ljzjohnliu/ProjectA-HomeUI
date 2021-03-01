package com.study.homeui.bean;

import com.study.homeui.adapter.NewWorldAdapter;
import com.study.homeui.adapter.home.IHomeInterface;

import java.util.ArrayList;

public class HomeTitleItem implements IHomeInterface {

    private String cityImg;
    private int clubNum;
    private ArrayList<HomeContentItem> cityClubList;

    public String getCityImg() {
        return cityImg;
    }

    public void setCityImg(String cityImg) {
        this.cityImg = cityImg;
    }

    public int getClubNum() {
        return clubNum;
    }

    public void setClubNum(int clubNum) {
        this.clubNum = clubNum;
    }

    public boolean isShowExpandBtn() {
        return clubNum > 3;
    }

    public ArrayList<HomeContentItem> getCityClubList() {
        return cityClubList;
    }

    public void setCityClubList(ArrayList<HomeContentItem> cityClubList) {
        this.cityClubList = cityClubList;
    }

    @Override
    public int getType() {
        return NewWorldAdapter.VIEW_TYPE_HEAD;
    }

    @Override
    public boolean isTitle() {
        return true;
    }
}

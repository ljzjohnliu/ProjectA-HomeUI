package com.study.homeui.bean;

import com.study.homeui.adapter.NewWorldAdapter;
import com.study.homeui.adapter.home.IHomeInterface;
import com.study.homeui.widget.CustomModel;

import java.util.List;

public class HomeContentItem implements IHomeInterface {

    private String title;
    private int hongbaoNum;
    private String des;
    private List<CustomModel> models;
    private List<String> avatarList;
    private int totalNum;
    private String coverImg;
    private int userClubStatus;//用户入群状态 0:未入群 1:已入群 2:审核中
    private String groupId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHongbaoNum() {
        return hongbaoNum;
    }

    public void setHongbaoNum(int hongbaoNum) {
        this.hongbaoNum = hongbaoNum;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<CustomModel> getModels() {
        return models;
    }

    public void setModels(List<CustomModel> models) {
        this.models = models;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<String> getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(List<String> avatarList) {
        this.avatarList = avatarList;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getUserClubStatus() {
        return userClubStatus;
    }

    public void setUserClubStatus(int userClubStatus) {
        this.userClubStatus = userClubStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public int getType() {
        return NewWorldAdapter.VIEW_TYPE_CONTENT;
    }

    @Override
    public boolean isTitle() {
        return false;
    }
}

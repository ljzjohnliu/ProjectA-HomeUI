package com.study.homeui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hanter.xpulltorefresh.PullToRefreshLayout;
import com.study.homeui.adapter.HomeAdapter;
import com.study.homeui.adapter.home.IHomeInterface;
import com.study.homeui.bean.HomeContentItem;
import com.study.homeui.bean.HomeTitleItem;
import com.study.homeui.utils.SystemUIUtils;
import com.study.homeui.utils.ToastUtil;
import com.study.homeui.widget.CustomModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private HomeAdapter mNewWorldAdapter;
    private List<IHomeInterface> newWorldList = new ArrayList<>();
    private Handler mHandler = new Handler();

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fragment_container)
    FragmentContainerView fragmentContainer;
    @BindView(R.id.recyclerView_head)
    View recyclerViewHead;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.nestedscrollview)
    NestedScrollView nestedscrollview;
    @BindView(R.id.home_swipe_layout)
    PullToRefreshLayout homeSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SystemUIUtils.setupTranslucentSystemBar(this);
        SystemUIUtils.setSystemBarTitle(this);

        mockAvatarList();
        mockData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNewWorldAdapter = new HomeAdapter(newWorldList);
        mRecyclerView.setAdapter(mNewWorldAdapter);
        mNewWorldAdapter.notifyDataSetChanged();

        homeSwipeLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDownToRefresh(PullToRefreshLayout refreshView) {
                // 下拉刷新
                Log.d(TAG, "onPullDownToRefresh: ---------------------------");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: ************completePullDownRefresh***********");
                        homeSwipeLayout.completePullDownRefresh();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshLayout refreshView) {
                // 上拉刷新
                Log.d(TAG, "onPullUpToRefresh: ----------------------------");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: ************completePullDownRefresh***********");
                        homeSwipeLayout.completePullUpRefresh();
                    }
                }, 1000);
            }
        });

    }

    @OnClick(R.id.fragment_container)
    public void onClick() {
        Log.d(TAG, "onClick: fragment_container is clicked!!");
//        Toast.makeText(this, "底层view被点击了！", Toast.LENGTH_SHORT).show();
        ToastUtil.showGreenToast(this, "底层view被点击了！");
    }

    private List<String> avatarList = new ArrayList<>();

    private void mockAvatarList() {
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223950_vygmz.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223952_zfhli.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201810/30/20181030153225_mixve.thumb.700_0.jpg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201807/08/20180708095827_SYPL3.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201811/01/20181101093301_u2NKu.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223950_vygmz.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg");
        avatarList.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223952_zfhli.thumb.700_0.jpeg");
    }


    private List<CustomModel> mockComment(String city, String title) {
        List<CustomModel> models = new ArrayList<>();
        models.add(new CustomModel(
                10000,
                getString(R.string.comment_str, city, title + "1"),
                "有可爱的小姐姐吗？认领一只\uD83D\uDE42"
        ));
        models.add(new CustomModel(
                10001,
                getString(R.string.comment_str, city, title + "2"),
                "有可爱的小姐姐吗？认领一只\uD83D\uDE42"
        ));
        models.add(new CustomModel(
                10002,
                getString(R.string.comment_str, city, title + "3"),
                "有可爱的小姐姐吗？认领一只\uD83D\uDE42"
        ));
        return models;
    }

    private void mockData() {
        HomeTitleItem shTitleItem = new HomeTitleItem();
        shTitleItem.setCityImg("上海群");
        shTitleItem.setClubNum(1);
        newWorldList.add(shTitleItem);
        HomeContentItem shContentItem1 = new HomeContentItem();
        shContentItem1.setTitle("上海群-测试群1");
        shContentItem1.setAvatarList(avatarList);
        shContentItem1.setTotalNum(120);
        shContentItem1.setModels(mockComment("上海群", "测试群"));
        newWorldList.add(shContentItem1);

        HomeTitleItem bjTitleItem = new HomeTitleItem();
        bjTitleItem.setCityImg("北京群");
        bjTitleItem.setClubNum(4);
        newWorldList.add(bjTitleItem);
        HomeContentItem bjContentItem1 = new HomeContentItem();
        bjContentItem1.setTitle("北京群-测试群1");
        bjContentItem1.setAvatarList(avatarList);
        bjContentItem1.setTotalNum(120);
        bjContentItem1.setModels(mockComment("北京群", "测试群"));
        newWorldList.add(bjContentItem1);
        HomeContentItem bjContentItem2 = new HomeContentItem();
        bjContentItem2.setTitle("北京群-测试群2");
        bjContentItem2.setAvatarList(avatarList);
        bjContentItem2.setTotalNum(120);
        bjContentItem2.setModels(mockComment("北京群", "测试群"));
        newWorldList.add(bjContentItem2);
        HomeContentItem bjContentItem3 = new HomeContentItem();
        bjContentItem3.setTitle("北京群-测试群3");
        bjContentItem3.setAvatarList(avatarList);
        bjContentItem3.setTotalNum(120);
        bjContentItem3.setModels(mockComment("北京群", "测试群"));
        newWorldList.add(bjContentItem3);
        HomeContentItem bjContentItem4 = new HomeContentItem();
        bjContentItem4.setTitle("北京群-测试群4");
        bjContentItem4.setAvatarList(avatarList);
        bjContentItem4.setTotalNum(120);
        bjContentItem4.setModels(mockComment("北京群", "测试群"));
        newWorldList.add(bjContentItem4);

        HomeTitleItem hzTitleItem = new HomeTitleItem();
        hzTitleItem.setCityImg("杭州群");
        hzTitleItem.setClubNum(2);
        newWorldList.add(hzTitleItem);
        HomeContentItem hzContentItem1 = new HomeContentItem();
        hzContentItem1.setTitle("杭州群-测试群1");
        hzContentItem1.setAvatarList(avatarList);
        hzContentItem1.setTotalNum(120);
        hzContentItem1.setModels(mockComment("杭州群", "测试群"));
        newWorldList.add(hzContentItem1);
        HomeContentItem hzContentItem2 = new HomeContentItem();
        hzContentItem2.setTitle("杭州群-测试群2");
        hzContentItem2.setAvatarList(avatarList);
        hzContentItem2.setTotalNum(120);
        hzContentItem2.setModels(mockComment("杭州群", "测试群"));
        newWorldList.add(hzContentItem2);
    }
}
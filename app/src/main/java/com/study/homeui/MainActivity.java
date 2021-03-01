package com.study.homeui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.homeui.adapter.SimpleTestAdapter;
import com.study.homeui.utils.SystemUIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fragment_container)
    FragmentContainerView fragmentContainer;
    @BindView(R.id.recyclerView_head)
    View recyclerViewHead;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nestedscrollview)
    NestedScrollView nestedscrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SystemUIUtils.setupTranslucentSystemBar(this);
        SystemUIUtils.setSystemBarTitle(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new SimpleTestAdapter());

    }

    @OnClick(R.id.fragment_container)
    public void onClick() {
        Log.d(TAG, "onClick: fragment_container is clicked!!");
    }
}
package com.yangzheandroid.rxmvp.view.activity.customview;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yangzheandroid.rxmvp.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomViewListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    private Unbinder mUnbinder;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_view_list);
        mUnbinder = ButterKnife.bind(this);

        mSwipeRefreshWidget.setColorSchemeColors(R.color.red,Color.GREEN,Color.BLUE);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    protected void initPresenter() {

    }


    @Override
    public void onRefresh() {

    }
}

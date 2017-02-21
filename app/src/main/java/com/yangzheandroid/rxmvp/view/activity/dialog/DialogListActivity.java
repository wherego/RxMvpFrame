package com.yangzheandroid.rxmvp.view.activity.dialog;

import android.os.Bundle;

import com.yangzheandroid.rxmvp.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.presenter.dialog.DialogListPresenter;
import com.yangzheandroid.rxmvp.widget.dialog.PrettyProgressDialog;
import com.yangzheandroid.rxmvp.widget.refreshload.PullRecyclerView;
import com.yangzheandroid.rxmvp.widget.refreshload.PullRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogListActivity extends BaseActivity implements DialogListConstract.View {
    @BindView(R.id.dialog_list)
    PullRecyclerView mDialogList;
    @BindView(R.id.prl_layout)
    PullRefreshLayout mPrlLayout;
    private DialogListPresenter mPresenter;
    private Unbinder mUnbinder;
    private PrettyProgressDialog mPrettyProgressDialog;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dialog_list);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.release();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new DialogListPresenter(this);
        mPresenter.start();
    }


    @Override
    public void showLoading() {
        mPrettyProgressDialog = showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        stopLoadingDialog();
    }

    @Override
    public PullRecyclerView getRecycleView() {
        return mDialogList;
    }

    @Override
    public PullRefreshLayout getPullToRefresh() {
        return mPrlLayout;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

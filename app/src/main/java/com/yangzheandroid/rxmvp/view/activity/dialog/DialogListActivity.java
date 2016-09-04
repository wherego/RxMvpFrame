package com.yangzheandroid.rxmvp.view.activity.dialog;

import android.support.v7.widget.RecyclerView;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.presenter.dialog.DialogListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogListActivity extends BaseActivity implements DialogListConstract.View {


    @BindView(R.id.dialog_list)
    RecyclerView mDialogList;
    private DialogListPresenter mPresenter;
    private Unbinder mUnbinder;
    private SweetAlertDialog mSweetAlertDialog;

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
        mSweetAlertDialog = showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        stopLoadingDialog();
    }

    @Override
    public RecyclerView getRecycleView() {
        return mDialogList;
    }


}

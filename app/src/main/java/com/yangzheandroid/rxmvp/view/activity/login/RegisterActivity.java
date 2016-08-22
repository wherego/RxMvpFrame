package com.yangzheandroid.rxmvp.view.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.presenter.login.RegisterPresenter;
import com.yangzheandroid.rxmvp.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    @BindView(R.id.et_register_pnumber)
    EditText mEtRegisterPnumber;
    @BindView(R.id.et_register_code)
    EditText mEtRegisterCode;
    @BindView(R.id.tv_getSmsCode)
    TextView mTvGetSmsCode;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    private SweetAlertDialog mSweetAlertDialog;
    private Unbinder mUnbinder;
    private RegisterPresenter mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = new RegisterPresenter(RegisterActivity.this);
        mPresenter.start();
    }

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
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
    public String getUserName() {
        return mEtRegisterPnumber.getText().toString();
    }

    @Override
    public String getSmsCode() {
        return mEtRegisterCode.getText().toString();
    }


    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void changeSmsText(String content) {
        mTvGetSmsCode.setText(content);
    }

    @Override
    public void setClickable(boolean able) {
        mTvGetSmsCode.setClickable(able);
    }


    @OnClick({R.id.tv_getSmsCode, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getSmsCode:
                mPresenter.getSmsCode();
                break;
            case R.id.btn_register:
                mPresenter.register();
                break;
        }
    }
}

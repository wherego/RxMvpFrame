package com.yangzheandroid.rxmvp.view.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.presenter.login.ForgetPsdPresenter;
import com.yangzheandroid.rxmvp.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgetPsdActivity extends BaseActivity implements ForgetPsdContract.View {


    @BindView(R.id.et_forget_psd_user)
    EditText mEtForgetPsdUser;
    @BindView(R.id.et_forget_old_psd)
    EditText mEtForgetOldPsd;
    @BindView(R.id.et_forget_new_psd)
    EditText mEtForgetNewPsd;
    @BindView(R.id.et_forget_new_psd_confirm)
    EditText mEtForgetNewPsdConfirm;
    @BindView(R.id.btn_forget_psd)
    Button mBtnForgetPsd;
    private Unbinder mUnbinder;
    private SweetAlertDialog mSweetAlertDialog;
    private ForgetPsdPresenter mPresenter;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forget_psd);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ForgetPsdPresenter(this);
    }

    @OnClick(R.id.btn_forget_psd)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_psd:
                mPresenter.modifyUserPsd();
                break;
            default:

                break;

        }
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
        return mEtForgetPsdUser.getText().toString();
    }

    @Override
    public String getOldPassword() {
        return mEtForgetOldPsd.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return mEtForgetNewPsd.getText().toString();
    }

    @Override
    public String getNewPasswordConfirm() {
        return mEtForgetNewPsdConfirm.getText().toString();
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(message);
    }
}

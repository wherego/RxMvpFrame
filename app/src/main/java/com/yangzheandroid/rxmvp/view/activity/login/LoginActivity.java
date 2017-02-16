package com.yangzheandroid.rxmvp.view.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.base.BaseActivity;
import com.yangzheandroid.rxmvp.presenter.login.LoginPresenter;
import com.yangzheandroid.rxmvp.utils.ToastUtils;
import com.yangzheandroid.rxmvp.widget.dialog.PrettyProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_login_pnumber)
    EditText mEtLoginPnumber;
    @BindView(R.id.et_login_psd)
    EditText mEtLoginPsd;
    @BindView(R.id.tv_password)
    TextView mTvPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_wenti)
    TextView mTvWenti;
    private Unbinder mUnbinder;
    private LoginPresenter mLoginPresenter;
    private PrettyProgressDialog mPrettyProgressDialog;

    @Override
    protected void initPresenter() {

        mLoginPresenter = new LoginPresenter(LoginActivity.this);
    }

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_wenti})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mLoginPresenter.isLoginSuccess();
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.tv_wenti:
                startActivity(new Intent(LoginActivity.this,ForgetPsdActivity.class));
                break;
        }
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
    public String getUserName() {
        return mEtLoginPnumber.getText().toString();
    }

    @Override
    public String getUserPsd() {
        return mEtLoginPsd.getText().toString();
    }


    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(message);
    }
}

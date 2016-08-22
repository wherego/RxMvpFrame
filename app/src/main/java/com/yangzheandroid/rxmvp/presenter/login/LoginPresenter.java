package com.yangzheandroid.rxmvp.presenter.login;

import com.yangzheandroid.retrofitutils.rx.RxSchedulersHelper;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.http.rx.RxResultHelper;
import com.yangzheandroid.rxmvp.http.rx.RxSubscriber;
import com.yangzheandroid.rxmvp.model.login.LoginIml;
import com.yangzheandroid.rxmvp.utils.ToastUtils;
import com.yangzheandroid.rxmvp.view.activity.login.LoginContract;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Author：Jalen on 2016/7/17 11:19
 * Contact: studylifetime@sina.com
 */
public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    LoginContract.View mView;
    LoginIml mLoginIml;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mLoginIml = new LoginIml();
    }


    @Override
    public void start() {

    }

    @Override
    public void isLoginSuccess() {
        Subscription subscribe = mLoginIml.loginRequest(mView.getUserName(), mView.getUserPsd())
                .compose(RxSchedulersHelper.<BaseCallModel<UserBean>>io_main())
                .compose(RxResultHelper.<UserBean>handleResult())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mView.hideLoading();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hideLoading();
                    }
                })
                .subscribe(new RxSubscriber<UserBean>() {
                    @Override
                    public void _onNext(UserBean userBean) {

                    }

                    @Override
                    public void _onCompleted() {

                    }

                    @Override
                    public void _onError(String msg) {

                        ToastUtils.showToast("登录失败" + msg);
                    }
                });
    }
}

package com.yangzheandroid.rxmvp.presenter.login;

import android.content.Context;

import com.yangzheandroid.retrofitutils.rx.RxSchedulersHelper;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.http.callback.CallBackStore;
import com.yangzheandroid.rxmvp.http.rx.RxResultHelper;
import com.yangzheandroid.rxmvp.http.rx.RxSubscriber;
import com.yangzheandroid.rxmvp.model.login.ForgetPsdIml;
import com.yangzheandroid.rxmvp.utils.CommonUtils;
import com.yangzheandroid.rxmvp.utils.ToastUtils;
import com.yangzheandroid.rxmvp.view.activity.login.ForgetPsdContract;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 采贝科技有限公司  版权所有(c)
 * Author：yangjilai
 * Time: 2016/8/6 11:37
 * <p>
 * 功能描述:
 */
public class ForgetPsdPresenter implements ForgetPsdContract.Presenter {
    ForgetPsdContract.View mView;
    ForgetPsdIml mForgetPsdIml;

    public ForgetPsdPresenter(ForgetPsdContract.View view) {
        mView = view;
        mForgetPsdIml = new ForgetPsdIml();
    }

    @Override
    public void modifyUserPsd() {
        CommonUtils.getToken((Context) mView, new CallBackStore.TokenCallBack() {
            @Override
            public void onSuccess(String token) {
                forgetPsd(token);
            }

            @Override
            public void onFailed() {
                ToastUtils.showToast("未登录");
            }
        });

    }

    /**
     * 忘记密码
     *
     * @param token
     */
    private void forgetPsd(String token) {

        Subscription subscribe = mForgetPsdIml.modifyUserPsd(token, mView.getOldPassword(), mView.getNewPassword())
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
                        mView.showMessage("修改密码成功");
                    }

                    @Override
                    public void _onCompleted() {

                    }

                    @Override
                    public void _onError(String msg) {
                        mView.showMessage(msg);

                    }
                });



    }

    @Override
    public void start() {

    }

    @Override
    public void release() {

    }


}

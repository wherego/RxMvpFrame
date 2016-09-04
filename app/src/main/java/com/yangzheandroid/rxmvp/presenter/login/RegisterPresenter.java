package com.yangzheandroid.rxmvp.presenter.login;

import com.yangzheandroid.retrofitutils.rx.RxSchedulersHelper;
import com.yangzheandroid.retrofitutils.utils.CountNumTimer;
import com.yangzheandroid.retrofitutils.utils.LogUtil;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.http.rx.RxResultHelper;
import com.yangzheandroid.rxmvp.http.rx.RxSubscriber;
import com.yangzheandroid.rxmvp.model.login.RegisterIml;
import com.yangzheandroid.rxmvp.utils.ToastUtils;
import com.yangzheandroid.rxmvp.view.activity.login.RegisterContract;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Author：Jalen on 2016/7/17 13:01
 * Contact: studylifetime@sina.com
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private static final String TAG = RegisterPresenter.class.getSimpleName();
    RegisterContract.View mView;
    private int Count = 60;
    private final RegisterIml mRegisterIml;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mRegisterIml = new RegisterIml();
    }

    @Override
    public void register() {

        Subscription subscribe = mRegisterIml.registerRequest(mView.getUserName(), "123456", mView.getSmsCode())
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
                        LogUtil.e(TAG, "网络错误" + throwable);
                    }
                }).subscribe(new RxSubscriber<UserBean>() {
                    @Override
                    public void _onNext(UserBean userBean) {
                        ToastUtils.showToast("注册成功  " + userBean.getRetCode());
                    }

                    @Override
                    public void _onCompleted() {

                    }

                    @Override
                    public void _onError(String msg) {
                        ToastUtils.showToast("注册失败    " + msg);
                    }
                });


    }

    @Override
    public void getSmsCode() {
        new CountNumTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Count--;
                mView.changeSmsText(Count + "后重新发送");
                mView.setClickable(false);
            }

            @Override
            public void onFinish() {
                mView.changeSmsText("重新获取验证码");
                mView.setClickable(true);
                Count = 60;
            }
        }.start();

//        mRegisterResponstory.getSmsCode(mView.getSmsCode())
//                .compose(RxSchedulersHelper.<Integer>io_main())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        mView.showLoading();
//                    }
//                }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                mView.hideLoading();
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                ToastUtils.showToast("注册失败");
//                mView.hideLoading();
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                mView.hideLoading();
//            }
//        });

    }

    @Override
    public void start() {

    }

    @Override
    public void release() {

    }
}

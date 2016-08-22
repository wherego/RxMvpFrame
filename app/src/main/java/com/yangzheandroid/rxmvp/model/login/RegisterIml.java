package com.yangzheandroid.rxmvp.model.login;

import com.yangzheandroid.retrofitutils.HttpProtol;
import com.yangzheandroid.rxmvp.api.ApiStore;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.view.activity.login.RegisterContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Author：Jalen on 2016/7/17 17:22
 * Contact: studylifetime@sina.com
 */
public class RegisterIml implements RegisterContract.Model {


    @Override
    public Observable<BaseCallModel<UserBean>> registerRequest(final String username, final String userpsd, final String auth) {
        return Observable.create(new Observable.OnSubscribe<BaseCallModel<UserBean>>() {
            @Override
            public void call(final Subscriber<? super BaseCallModel<UserBean>> subscriber) {
                HttpProtol.newBuilder()
                        .build()
                        .create(ApiStore.class)
                        .register(username, userpsd, auth)
                        .subscribe(new Subscriber<BaseCallModel<UserBean>>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(BaseCallModel<UserBean> userBeanBaseCallModel) {
                                subscriber.onNext(userBeanBaseCallModel);
                            }
                        });
            }
        });
    }

    @Override
    public Observable<Integer> getSmsCode(final String phoneNum) {

        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {

//                BmobSMS.requestSMSCode(phoneNum, "手机注册", new QueryListener<Integer>() {
//
//                    @Override
//                    public void done(Integer integer, cn.bmob.v3.exception.BmobException e) {
//                        if (e == null) {//验证码发送成功
//                            subscriber.onNext(integer);
//                            subscriber.onCompleted();
//                        } else {
//                            subscriber.onError(e);
//                        }
//                    }
//                });
            }
        });
    }

}

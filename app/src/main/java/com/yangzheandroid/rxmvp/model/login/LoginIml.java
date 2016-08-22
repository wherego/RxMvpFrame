package com.yangzheandroid.rxmvp.model.login;

import com.yangzheandroid.retrofitutils.HttpProtol;
import com.yangzheandroid.rxmvp.api.ApiStore;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.view.activity.login.LoginContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Author：Jalen on 2016/7/17 11:56
 * Contact: studylifetime@sina.com
 */
public class LoginIml implements LoginContract.Model {
    @Override
    public Observable<BaseCallModel<UserBean>> loginRequest(final String username, final String userpsd) {
        return Observable.create(new Observable.OnSubscribe<BaseCallModel<UserBean>>() {
            @Override
            public void call(final Subscriber<? super BaseCallModel<UserBean>> subscriber) {
                HttpProtol.newBuilder()
                        .build()
                        .create(ApiStore.class)
                        .login(username, userpsd)
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

}

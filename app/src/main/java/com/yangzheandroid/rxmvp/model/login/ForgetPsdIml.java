package com.yangzheandroid.rxmvp.model.login;

import com.yangzheandroid.retrofitutils.HttpProtol;
import com.yangzheandroid.rxmvp.api.ApiStore;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;
import com.yangzheandroid.rxmvp.view.activity.login.ForgetPsdContract;

import rx.Observable;
import rx.Subscriber;


public class ForgetPsdIml implements ForgetPsdContract.Model {
    @Override
    public Observable<BaseCallModel<UserBean>> modifyUserPsd(final String token, final String oldpassword, final String newpassword) {
        return Observable.create(new Observable.OnSubscribe<BaseCallModel<UserBean>>() {
            @Override
            public void call(final Subscriber<? super BaseCallModel<UserBean>> subscriber) {
                HttpProtol.newBuilder()
                        .build()
                        .create(ApiStore.class)
                        .modifyUserPsd(token, oldpassword, newpassword)
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

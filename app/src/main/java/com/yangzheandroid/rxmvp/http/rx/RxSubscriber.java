package com.yangzheandroid.rxmvp.http.rx;

import com.yangzheandroid.retrofitutils.exception.ServerException;
import com.yangzheandroid.retrofitutils.utils.NetUtils;
import com.yangzheandroid.rxmvp.APP;

import rx.Subscriber;

/**
 * 封装Subscriber
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        _onCompleted();

        unsubscribeMethod();
    }



    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        if (!NetUtils.isNetworkAvailable(APP.getInstance())) {
            _onError("网络不可用!");
            return;
        }

        //返回code=-1 的异常
        if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {//返回的是返回成功后,还有异常
            _onError(e.getMessage());
        }

        unsubscribeMethod();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onCompleted();

    public abstract void _onError(String msg);


    private void unsubscribeMethod() {
        if (!isUnsubscribed()) {
            unsubscribe();
        }
    }
}
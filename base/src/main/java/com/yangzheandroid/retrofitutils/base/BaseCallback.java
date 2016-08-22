package com.yangzheandroid.retrofitutils.base;

/**
 * Author：kalu on 2016/7/20 11:34
 * Contact: 153437803@qq.com
 */
public interface BaseCallback<T> {

    void onLoading();

    void onFinish();

    void onSuccess(T t);

    void onFailed(Exception e);
}

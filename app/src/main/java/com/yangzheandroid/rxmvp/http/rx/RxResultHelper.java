package com.yangzheandroid.rxmvp.http.rx;

import android.support.annotation.NonNull;

import com.yangzheandroid.retrofitutils.exception.ServerException;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Author：SEELE on 2016/7/22 15:45
 * Contact: studylifetime@sina.com
 * Rx处理服务器返回
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<BaseCallModel<T>, T> handleResult() {
        return new Observable.Transformer<BaseCallModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseCallModel<T>> tObservable) {
                return tObservable.flatMap(
                        new Func1<BaseCallModel<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(final BaseCallModel<T> result) {
                                /**
                                 *  code=0  返回数据
                                 *  code!=0 服务器返回什么是什么
                                 *
                                 */
                                if (result.getRetCode() == BaseCallModel.SUCCESS) {
                                    return createData(result.data);
                                } else {
                                    return getTObservable(result.getRetCode(), result.getRetMsg());
                                }

                            }
                        }

                );
            }

            /**
             * 错误返回异常的方法
             * @param message
             * @return
             */
            @NonNull
            private Observable<T> getTObservable(final int code, final String message) {
                return Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            new ServerException(code + File.separator + message);
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                });
            }
        };
    }

    /**
     * 返回数据的方法
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}

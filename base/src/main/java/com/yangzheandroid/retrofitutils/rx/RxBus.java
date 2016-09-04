package com.yangzheandroid.retrofitutils.rx;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;
import rx.subjects.SerializedSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Author：SEELE on 2016/7/25 14:32
 * Contact: studylifetime@sina.com
 */
public class RxBus {

    private static volatile RxBus mInstance;
    private final SerializedSubject<Object, Object> mSubject;
    private HashMap<String, CompositeSubscription> mSubscriptionHashMap;

    private RxBus() {
        //StickyEvent功能的添加
        mSubject = new SerializedSubject<>(ReplaySubject.create());
    }

    /**
     * 单例RxBus
     *
     * @return
     */
    public static RxBus getDefault() {
        RxBus rxBus = mInstance;
        if (mInstance == null) {
            synchronized (RxBus.class) {
                rxBus = mInstance;
                if (mInstance == null) {
                    rxBus = new RxBus();
                    mInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    /**
     * 发送一个新事件
     *
     * @param o
     */
    public void post(Object o) {
        mSubject.onNext(o);
    }

    /**
     * 返回特定类型的被观察者
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mSubject.ofType(eventType);
    }

    /**
     * 是否已经有观察值订阅
     *
     * @return
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * 一个默认的订阅方法
     *
     * @param type
     * @param next
     * @param error
     * @param <T>
     * @return
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error) {
        Subscription subscription = toObservable(type)
                .compose(RxSchedulersHelper.<T>io_main())
                .subscribe(next, error);

        addSubscription(type,subscription);
        return subscription;
    }

    /**
     * 保存订阅后的subscription
     *
     * @param o
     * @param subscription
     */
    public void addSubscription(Object o, Subscription subscription) {
        if (mSubscriptionHashMap == null) {
            mSubscriptionHashMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionHashMap.get(key) != null) {
            mSubscriptionHashMap.get(key).add(subscription);
        } else {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            mSubscriptionHashMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     *
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionHashMap == null) {
            return;
        }
        String key = o.getClass().getName();
        if (!mSubscriptionHashMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionHashMap.get(key) != null) {
            mSubscriptionHashMap.get(key).unsubscribe();
        }
        mSubscriptionHashMap.remove(key);
    }

}

package com.yangzheandroid.rxmvp;

import android.app.Application;

import com.yangzheandroid.rxmvp.hotfix.HotFixManger;

public class APP extends Application {

    private static APP mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        init();
    }

    public static APP getInstance() {
        return mApplication;
    }

    private void init() {

        HotFixManger.init(this);

    }



}

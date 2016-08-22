package com.yangzheandroid.rxmvp.utils;

import com.yangzheandroid.rxmvp.APP;

/**
 * Author：Jalen on 2016/8/21 11:43
 * Contact: studylifetime@sina.com
 */
public class ToastUtils {

    public static void showToast(String message) {
        com.yangzheandroid.retrofitutils.utils.ToastUtils.showToast(APP.getInstance(), message);

    }
}

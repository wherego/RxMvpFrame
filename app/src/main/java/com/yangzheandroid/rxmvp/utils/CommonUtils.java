package com.yangzheandroid.rxmvp.utils;

import android.content.Context;

import com.yangzheandroid.rxmvp.http.callback.CallBackStore;


/**
 * 采贝科技有限公司  版权所有(c)
 * Author：yangjilai
 * Time: 2016/8/6 11:46
 * <p/>
 * 功能描述:
 */
public class CommonUtils {

    /**
     * 查看是否登录的方法
     *
     * @param context
     * @param callback
     */
    public static void getToken(Context context, CallBackStore.TokenCallBack callback) {
        String token = (String) SPUtils.get(context, "Token", "");
        //检测本地是否存在Token
//        if (!"".equals(token)) {
            callback.onSuccess(token);
//        } else {
//            callback.onFailed();
//        }


    }


}



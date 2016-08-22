package com.yangzheandroid.rxmvp.http.callback;

/**
 * 采贝科技有限公司  版权所有(c)
 * Author：yangjilai
 * Time: 2016/8/6 11:54
 * <p/>
 * 功能描述:  回调方法的汇总
 */
public interface CallBackStore {

    /**
     * 验证登录的回调
     */
    interface TokenCallBack {
        void onSuccess(String token);

        void onFailed();
    }

}

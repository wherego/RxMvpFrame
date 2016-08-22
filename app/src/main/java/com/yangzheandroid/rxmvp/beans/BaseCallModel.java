package com.yangzheandroid.rxmvp.beans;

/**
 * =========================================================
 * <p/>
 * 版权: 公司开发 采贝科技  版权所有(c)
 * <p/>
 * <p/>
 * 版本: 1.0.0
 * <p/>
 * <p/>
 * 创建日期 : 2016/8/2  14:22
 * <p/>
 * <p/>
 * 描述:  接口接收的类
 * <p/>
 * <p/>
 * 修订历史:
 * <p/>
 * =========================================================
 */
public class BaseCallModel<T> {
    public static final int SUCCESS = 0;
    public static int Failed = 2001;
    public static int UnLogin = 1000;

    private int retCode;
    private String retMsg;

    public T data;  // 包装的对象

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
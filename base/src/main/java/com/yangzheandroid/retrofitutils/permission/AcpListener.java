package com.yangzheandroid.retrofitutils.permission;

import java.util.List;

/**
 * =========================================================
 * <p>
 * 版权: 采贝科技公司开发 版权所有(c)
 * <p>
 * 作者:Yangjilai
 * <p>
 * 版本: 1.0.0
 * <p>
 * <p>
 * 创建日期 : 2016/8/9  16:34
 * <p>
 * <p>
 * 描述:  监听器
 * <p>
 * <p>
 * 修订历史:
 * <p>
 * =========================================================
 */
public interface AcpListener {
    /**
     * 同意
     */
    void onGranted();

    /**
     * 拒绝
     */
    void onDenied(List<String> permissions);
}

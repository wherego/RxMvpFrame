package com.yangzheandroid.rxmvp.model;

import java.util.List;

/**
 * Author：Jalen on 2016/9/4 19:59
 * Contact: studylifetime@sina.com
 */
public interface OnRequestListenter {
    void onSuccess(List<String> object);

    void onError();

}

package com.yangzheandroid.rxmvp.view.activity.login;

import com.yangzheandroid.retrofitutils.base.BasePresenter;
import com.yangzheandroid.retrofitutils.base.BaseView;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;

import rx.Observable;

/**
 * Author：Jalen on 2016/7/17 11:09
 * Contact: studylifetime@sina.com
 */
public interface LoginContract {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        String getUserName();

        String getUserPsd();

        void showMessage(String message);

    }

    interface Presenter extends BasePresenter {
        void isLoginSuccess();
    }


    interface Model {
        Observable<BaseCallModel<UserBean>> loginRequest(String username, String userpsd);
    }
}

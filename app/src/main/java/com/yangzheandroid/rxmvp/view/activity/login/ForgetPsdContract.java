package com.yangzheandroid.rxmvp.view.activity.login;

import com.yangzheandroid.rxmvp.base.BasePresenter;
import com.yangzheandroid.rxmvp.base.BaseView;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;

import rx.Observable;


public interface ForgetPsdContract {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        String getUserName();

        String getOldPassword();

        String getNewPassword();

        String getNewPasswordConfirm();

        void showMessage(String message);

    }

    interface Presenter extends BasePresenter {
        void modifyUserPsd();
    }

    interface Model {
        Observable<BaseCallModel<UserBean>> modifyUserPsd(String token, String oldpassword, String newpassword);
    }
}

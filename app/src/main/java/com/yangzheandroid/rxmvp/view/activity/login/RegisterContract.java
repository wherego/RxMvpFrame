package com.yangzheandroid.rxmvp.view.activity.login;

import com.yangzheandroid.retrofitutils.base.BasePresenter;
import com.yangzheandroid.retrofitutils.base.BaseView;
import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.UserBean;

import rx.Observable;

/**
 * Author：Jalen on 2016/7/17 12:51
 * Contact: studylifetime@sina.com
 */
public interface RegisterContract {

    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        String getUserName();

        String getSmsCode();

        void showMessage(String message);

        void changeSmsText(String content);


        void setClickable(boolean able);
    }

    interface Presenter extends BasePresenter {

        void register();

        void getSmsCode();

    }



    interface Model {

        Observable<BaseCallModel<UserBean>> registerRequest(String username, String userpsd, String auth);

        Observable<Integer> getSmsCode(String phoneNum);

    }
}

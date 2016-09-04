package com.yangzheandroid.rxmvp.view.activity.dialog;

import com.yangzheandroid.retrofitutils.base.BasePresenter;
import com.yangzheandroid.retrofitutils.base.BaseView;
import com.yangzheandroid.rxmvp.model.OnRequestListenter;

/**
 * Author：Jalen on 2016/9/2 21:47
 * Contact: studylifetime@sina.com
 */
public class DialogListConstract {

    public interface View extends BaseView {
        android.support.v7.widget.RecyclerView getRecycleView();
    }

    public interface Presenter extends BasePresenter {

    }

    public interface Model {
        //Test
        void getData(OnRequestListenter lister);
    }
}

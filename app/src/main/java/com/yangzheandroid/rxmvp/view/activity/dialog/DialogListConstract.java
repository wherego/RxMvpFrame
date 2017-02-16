package com.yangzheandroid.rxmvp.view.activity.dialog;

import com.yangzheandroid.retrofitutils.base.BasePresenter;
import com.yangzheandroid.retrofitutils.base.BaseView;
import com.yangzheandroid.rxmvp.model.OnRequestListenter;
import com.yangzheandroid.rxmvp.widget.refreshload.PullRecyclerView;
import com.yangzheandroid.rxmvp.widget.refreshload.PullRefreshLayout;

/**
 * Author：Jalen on 2016/9/2 21:47
 * Contact: studylifetime@sina.com
 */
public class DialogListConstract {

    public interface View extends BaseView {
        PullRecyclerView getRecycleView();
        PullRefreshLayout getPullToRefresh();
    }

    public interface Presenter extends BasePresenter {

    }

    public interface Model {
        //Test
        void getData(OnRequestListenter lister);
    }
}

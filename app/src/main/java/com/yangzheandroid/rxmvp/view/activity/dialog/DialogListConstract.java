package com.yangzheandroid.rxmvp.view.activity.dialog;

import com.yangzheandroid.rxmvp.base.BasePresenter;
import com.yangzheandroid.rxmvp.base.BaseView;
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
}

package com.yangzheandroid.rxmvp.presenter.dialog;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangzheandroid.retrofitutils.utils.LogUtil;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.model.OnRequestListenter;
import com.yangzheandroid.rxmvp.model.dialog.DialogListIml;
import com.yangzheandroid.rxmvp.view.activity.dialog.DialogListActivity;
import com.yangzheandroid.rxmvp.view.activity.dialog.DialogListConstract;
import com.yangzheandroid.rxmvp.widget.dialog.BottomDialog;
import com.yangzheandroid.rxmvp.widget.dialog.SelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Jalen on 2016/9/2 21:58
 * Contact: studylifetime@sina.com
 */
public class DialogListPresenter implements DialogListConstract.Presenter {


    private static final String TAG = DialogListPresenter.class.getSimpleName();
    public DialogListActivity mView;
    public DialogListConstract.Model mModel;
    private final Handler mHandler;
    private ArrayList<String> mResult;

    public DialogListPresenter(DialogListActivity view) {
        mView = view;
        mModel = new DialogListIml();
        mHandler = new Handler(Looper.getMainLooper());
        mResult = new ArrayList<>();
    }


    @Override
    public void start() {

        mView.showLoading();

        mModel.getData(new OnRequestListenter() {
            @Override
            public void onSuccess(List<String> list) {

                LogUtil.e(TAG, list.toString());
                mResult = (ArrayList<String>) list;

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showData();
                        mView.hideLoading();
                    }
                });

            }

            @Override
            public void onError() {

            }
        });


    }

    private void showData() {
        mView.getRecycleView().setLayoutManager(new android.support.v7.widget.LinearLayoutManager(mView));
        mView.getRecycleView().setAdapter(new BaseQuickAdapter<String>(R.layout.itemlist_dialoglist, mResult) {


            @Override
            protected void convert(final BaseViewHolder baseViewHolder, final String str) {
                baseViewHolder.setText(R.id.txt_content, str);
            }
        });

        BaseQuickAdapter adapter = (BaseQuickAdapter) mView.getRecycleView().getAdapter();

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        SelectDialog dialog1 = new SelectDialog(mView);
                        dialog1.show();
                        break;
                    case 1:
                        BottomDialog bottomDialog = new BottomDialog(mView);
                        bottomDialog.show();
                        break;
                    default:

                        break;

                }
            }
        });
    }


    @Override
    public void release() {
        mView = null;
        //// TODO: 2016/9/2 取消网络请求
    }

}

package com.yangzheandroid.retrofitutils.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.yangzheandroid.retrofitutils.utils.AppManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Author：Jalen on 2016/7/17 10:16
 * Contact: studylifetime@sina.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    public SweetAlertDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVarlible();
        initView();
        initPresenter();
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  结束Activity&从堆栈中移除
        AppManager.getAppManager().removeActivity(this);
    }

    protected abstract void initVarlible();

    //布局文件ID
    protected abstract void initView();


    protected abstract void initPresenter();


    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 展示加载框
     */
    public SweetAlertDialog showLoadingDialog() {
        mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mLoadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mLoadingDialog.setTitleText("正在加载中...");
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.show();

        return mLoadingDialog;
    }

    /**
     * 停止对话框
     */
    public void stopLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}

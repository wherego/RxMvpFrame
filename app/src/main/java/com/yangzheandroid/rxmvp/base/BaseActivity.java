package com.yangzheandroid.rxmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.yangzheandroid.retrofitutils.utils.AppManager;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.widget.dialog.PrettyProgressDialog;


/**
 * Author：Jalen on 2016/7/17 10:16
 * Contact: studylifetime@sina.com
 */
public abstract class BaseActivity extends AppCompatActivity {


public  PrettyProgressDialog mPrettyProgressDialog;
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
    public PrettyProgressDialog showLoadingDialog() {
        mPrettyProgressDialog = new PrettyProgressDialog(this);
//        mPrettyProgressDialog.setText("正在加载中...");
        mPrettyProgressDialog.setText(R.string.dialong_loading_tips);
        mPrettyProgressDialog.show();
        return mPrettyProgressDialog;
    }

    /**
     * 停止对话框
     */
    public void stopLoadingDialog() {
        if (null != mPrettyProgressDialog && mPrettyProgressDialog.isShowing()) {
            mPrettyProgressDialog.dismiss();
            mPrettyProgressDialog = null;
        }
    }
}

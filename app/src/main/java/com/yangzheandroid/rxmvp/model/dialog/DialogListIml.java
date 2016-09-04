package com.yangzheandroid.rxmvp.model.dialog;

import android.os.SystemClock;

import com.yangzheandroid.rxmvp.model.OnRequestListenter;
import com.yangzheandroid.rxmvp.view.activity.dialog.DialogListConstract;

import java.util.ArrayList;

/**
 * Author：Jalen on 2016/9/2 22:02
 * Contact: studylifetime@sina.com
 */
public class DialogListIml implements DialogListConstract.Model {

    @Override
    public void getData(final OnRequestListenter lister) {
        final ArrayList<String> result = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                SystemClock.sleep(500);

                result.add("中间弹出对话框");
                result.add("底部弹出对话框");
                lister.onSuccess(result);
                return;
            }
        }).start();

    }


}

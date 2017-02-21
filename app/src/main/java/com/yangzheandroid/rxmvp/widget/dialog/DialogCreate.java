package com.yangzheandroid.rxmvp.widget.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author wl
 * @date 2017/1/11
 * @content 创建一个dialog的方法抽取
 */
public class DialogCreate {

    private static Activity mActivity;
    private static AlertDialog mAlertDialogs;

    private DialogCreate() {
    }

    public static DialogCreate getInstance(Activity activity) {
        mActivity = activity;
        return new DialogCreate();
    }

    public void createDialog(String title, String msg, boolean isCancecle, final DialogCallBack dialogCallBack) {
        if (mAlertDialogs == null) {
            AlertDialog show = new AlertDialog.Builder(mActivity)
                    .setTitle(title)
                    .setMessage(msg)
                    .setCancelable(isCancecle)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialogCallBack != null) {
                                dialogCallBack.callBackNegative();
                            }
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialogCallBack != null) {
                                dialogCallBack.callBackPositive();
                            }
                        }
                    }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            mAlertDialogs = null;
                        }
                    })
                    .show();
            mAlertDialogs = show;
        }
    }

    public void createDialogSingle(String title, String msg, boolean isCancecle, final SingleDialogCallBack dialogCallBack) {
        new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(isCancecle)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallBack != null) {
                            dialogCallBack.callBackPositive();
                        }

                    }
                })
                .show();
    }

    public interface DialogCallBack {
        void callBackPositive();

        void callBackNegative();
    }

    public interface SingleDialogCallBack {
        void callBackPositive();
    }
}

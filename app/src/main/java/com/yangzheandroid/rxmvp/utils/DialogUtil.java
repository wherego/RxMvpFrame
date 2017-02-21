package com.yangzheandroid.rxmvp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.widget.dialog.HintDialog;

/**
 * 采贝科技有限公司  版权所有(c)
 * Author：yangjilai
 * Time: 2017/2/21 14:34
 * <p>
 * 功能描述: dialog的工具类
 */
public class DialogUtil {
    /**
     * 只有一个Button的自定义对话框
     *
     * @param context
     * @param message
     * @param dialogCallBack
     */
    public static void showCustomSimpleDialog(Context context, String message, final SingleDialogCallBack dialogCallBack) {
        HintDialog dialog = new HintDialog(context);
        dialog.setTitle("强制更新");
        dialog.setBackgroundColor(context.getResources().getColor(R.color.white));
        dialog.setSingleText("立即更新", R.color.black);
        dialog.setLeftListener("立即更新", new HintDialog.OnLeftListener() {
            @Override
            public void onClick(HintDialog dialog) {
                if (null != dialogCallBack) {
                    dialogCallBack.callBackPositive(dialog);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 具有两个Button的自定义对话框
     *
     * @param context
     * @param message
     * @param dialogCallBack
     */
    public static void showCustomDialog(Context context, String message, final DoubleDialogCallBack dialogCallBack) {
        int color = context.getResources().getColor(R.color.black);
        HintDialog dialog2 = new HintDialog(context);
        dialog2.setTitle("强制更新");
        dialog2.setBackgroundColor(context.getResources().getColor(R.color.white));
        dialog2.setLeftListener("取消", color, new HintDialog.OnLeftListener() {
            @Override
            public void onClick(HintDialog dialog) {
                dialogCallBack.callBackPositive(dialog);
            }
        });
        dialog2.setRightListener("立即更新", color, new HintDialog.OnRightListener() {
            @Override
            public void onClick(HintDialog dialog) {
                dialogCallBack.callBackNegative(dialog);
            }
        });
        dialog2.show();
    }

    /**
     * 具有两个Buton的对话框,弹出的是V7包下的对话框
     *
     * @param context
     * @param title
     * @param message
     * @param isCancecle
     * @param dialogCallBack
     */
    public static void showDialog(Context context, String title, String message, boolean isCancecle, final DoubleDialogCallBack dialogCallBack) {
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(isCancecle)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallBack != null) {
                            dialogCallBack.callBackNegative(dialog);
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallBack != null) {
                            dialogCallBack.callBackPositive(dialog);
                        }
                    }
                })
                .show();

    }

    /**
     * 具有一个Buton的对话框,弹出的是V7包下的对话框
     *
     * @param context
     * @param title
     * @param message
     * @param isCancecle
     * @param dialogCallBack
     */
    public static void showSimpleDialog(Context context, String title, String message, boolean isCancecle, final SingleDialogCallBack dialogCallBack) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(isCancecle)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallBack != null) {
                            dialogCallBack.callBackPositive(dialog);
                        }
                    }
                })
                .show();

    }

    public interface DoubleDialogCallBack {
        void callBackPositive(DialogInterface dialog);

        void callBackNegative(DialogInterface dialog);
    }

    public interface SingleDialogCallBack {
        void callBackPositive(DialogInterface dialog);
    }
}

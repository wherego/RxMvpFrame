package com.yangzheandroid.retrofitutils.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 弹出框的工具类
 */
public class ToastUtils {

    private ToastUtils() {
    }

    public static void showToast(Context context,CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 中间显示
     *
     * @param text
     */
    public static void showToastCenter(Context context,String text) {
        Toast toast;
        if (text.length() < 10) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}

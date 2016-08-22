package com.yangzheandroid.retrofitutils.utils;


import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                Log.e("-------", "------RetrofitUtils-------流关闭异常");
            }
        }
        return true;
    }
}

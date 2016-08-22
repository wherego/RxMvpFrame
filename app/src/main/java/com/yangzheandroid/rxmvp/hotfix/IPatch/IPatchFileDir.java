package com.yangzheandroid.rxmvp.hotfix.IPatch;

import android.content.Context;

import java.io.File;

/**
 * Created by shoyu666@163.com on 16/7/5.
 */
public interface IPatchFileDir {
    /**
     * 返回patch保存路径
     * @return
     */
     File getPatchJarDir(Context context);

    /**
     * 返回当前的patch补丁文件,如果没有返回null
     * @return
     */
     File getCurrentPatchJar(Context context);

    /**
     * 返回保存patch补丁的文件名
     * @return
     */
     String getPatchFileName();
}

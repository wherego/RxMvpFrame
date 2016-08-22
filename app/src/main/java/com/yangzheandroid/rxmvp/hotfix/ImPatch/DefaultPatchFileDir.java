package com.yangzheandroid.rxmvp.hotfix.ImPatch;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.yangzheandroid.retrofitutils.permission.AcpListener;
import com.yangzheandroid.retrofitutils.permission.AcpOptions;
import com.yangzheandroid.retrofitutils.permission.AcpUtils;
import com.yangzheandroid.retrofitutils.utils.ToastUtils;
import com.yangzheandroid.rxmvp.APP;
import com.yangzheandroid.rxmvp.hotfix.IPatch.IPatchFileDir;

import java.io.File;
import java.util.List;

/**
 * 默认patch 路径管理
 * Created by shoyu666@163.com on 16/7/5.
 */
public class DefaultPatchFileDir implements IPatchFileDir {

    public static final String PachDir = "caibei";
    private static File sDir;

    @Override
    public File getPatchJarDir(Context context) {
//        return getHotFixPachDir();
        return getHotFixPachDirSDKCard(context);
    }

    @Override
    public File getCurrentPatchJar(Context context) {
        return new File(getPatchJarDir(context), getPatchFileName());
    }

    @Override
    public String getPatchFileName() {
        return "patch.jar";
    }

    public static File getHotFixPachDir() {
        File file = new File(APP.getInstance().getFilesDir(), PachDir);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * path放在sd卡上
     *
     * @return
     */
    public static File getHotFixPachDirSDKCard(final Context context) {

        AcpUtils.getInstance(context).request(new AcpOptions.Builder()
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .build(), new AcpListener() {
            @Override
            public void onGranted() {

                sDir = new File(getSDCardPath());

                if (!sDir.exists()) {
                    sDir.mkdirs();
                }

//                if (!sDir.canRead()) {
//
//                    ToastUtils.showToast("不能读取Sdcard权限");
//
//                    sDir = getHotFixPachDir();
//                }

            }

            @Override
            public void onDenied(List<String> permissions) {

                sDir = getHotFixPachDir();

                ToastUtils.showToast(context,"获取权限失败");
            }
        });

        return sDir;
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
}

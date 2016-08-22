package com.yangzheandroid.retrofitutils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

/**
 * =========================================================
 * <p>
 * 版权: 采贝科技公司开发 版权所有(c)
 * <p>
 * 作者:Yangjilai
 * <p>
 * 版本: 1.0.0
 * <p>
 * <p>
 * 创建日期 : 2016/8/9  16:35
 * <p>
 * <p>
 * 描述:  检查权限的工具
 * <p>
 * <p>
 * 修订历史:
 * <p>
 * =========================================================
 */
class AcpService {
    private static final String TAG = "AcpService";

    /**
     * 检查权限授权状态
     *
     * @param context
     * @param permission
     * @return
     */
    int checkSelfPermission(Context context, String permission) {
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int targetSdkVersion = info.applicationInfo.targetSdkVersion;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (targetSdkVersion >= Build.VERSION_CODES.M) {
                    Log.i(TAG, "targetSdkVersion >= Build.VERSION_CODES.M");
                    return ContextCompat.checkSelfPermission(context, permission);
                } else {
                    return PermissionChecker.checkSelfPermission(context, permission);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ContextCompat.checkSelfPermission(context, permission);
    }

    void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        boolean rationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        Log.i(TAG, "rationale = " + rationale);
        return rationale;
    }
}

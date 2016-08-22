package com.yangzheandroid.rxmvp.hotfix;

import android.content.Context;
import com.dodola.rocoofix.RocooFix;

import java.io.File;

/**
 * Rocoo统一入口
 * Created by shoyu666@163.com on 16/7/5.
 */
public class HotFixManger {
    /**
     * @param context
     * @CallBy attachBaseContext()
     * attachBaseContext 中调用Rocoo初始化和patch加载
     */
    public static void init(Context context) {
        try {

            RocooFix.init(context);

//           String proces = MyUtils.getCurProcessName(context);

//            if (!TextUtils.isEmpty(proces) && !proces.endsWith(":push")) {
            //因为我的项目有push 这里过滤掉push进程
            installPach(context);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void installPach(Context context) {

        File lastPach = PatchManger.getGlobalPatchManger().patchFileDir.getCurrentPatchJar(context);//获取目录

        if (lastPach != null && lastPach.exists() && lastPach.canRead()) {

            RocooFix.applyPatch(context, lastPach.getAbsolutePath());//静态制定目录更新的方式

//            RocooFix.applyPatchRuntime(context,lastPach.getAbsolutePath());//静态及时运行

            System.out.println("   installPach" + lastPach.getAbsolutePath());
        } else {
            System.out.println(" no  installPach");
        }
    }


    /**
     * 需要更新patch文件的时候调用
     */
    public static void updatePatchJar() {
        PatchManger.getGlobalPatchManger().patchDownloader.downloadPatch();
    }
}

package com.yangzheandroid.rxmvp.http.download;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.yangzheandroid.retrofitutils.HttpProtol;
import com.yangzheandroid.retrofitutils.base.Constant;
import com.yangzheandroid.retrofitutils.download.FileCallback;
import com.yangzheandroid.retrofitutils.utils.LogUtil;
import com.yangzheandroid.retrofitutils.utils.ToastUtils;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.api.ApiStore;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Author：SEELE on 2016/7/25 14:49
 * Contact: studylifetime@sina.com
 */
public class RxDownLoadService extends Service {

    private static final String TAG = RxDownLoadService.class.getSimpleName();
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName = "caibei.apk";

    private Context mContext;
    private int preProgress = 0;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                    .separator + "Test";
        } else {
            ToastUtils.showToast(this,"SD卡异常");
        }

        initNotification();

        // 使用Retrofit进行文件的下载
        HttpProtol.newBuilder()
                .baseUrl(Constant.BaseURL_DownLoad)
                .build()
                .create(ApiStore.class)
                .loadFile("BaiduMusic-pcwebdownload.apk")
                .enqueue(new FileCallback(destFileDir, destFileName) {

                    @Override
                    public void onSuccess(File file) {
                        LogUtil.e(TAG, "请求成功");
                        // 取消通知栏
                        cancelNotification();
                        //安装软件
                        installApk(file);
                    }

                    @Override
                    public void onLoading(long progress, long total) {
                        LogUtil.e(TAG, progress + "----" + total);
                        updateNotification(progress * 100 / total);// 更新前台通知
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LogUtil.e(TAG, "请求失败");
                        cancelNotification();// 取消通知
                    }
                });
    }


    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        mContext.startActivity(install);
    }


    /**
     * 初始化Notification通知
     */
    public void initNotification() {
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)// 设置通知的图标
                .setContentText("0%")// 进度Text
                .setContentTitle("自动更新")// 标题
                .setProgress(100, 0, false);// 设置进度条
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);// 获取系统通知管理器
        notificationManager.notify(NOTIFY_ID, builder.build());// 发送通知
    }

    /**
     * 更新通知
     */
    public void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        notificationManager.cancel(NOTIFY_ID);
    }
}

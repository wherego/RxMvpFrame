package com.yangzheandroid.retrofitutils.image.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.yangzheandroid.retrofitutils.utils.IOUtils;
import com.yangzheandroid.retrofitutils.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * =========================================================
 * <p/>
 * 版权: 个人开发Mr.Jalen  版权所有(c) ${YEAR}
 * <p/>
 * 作者:${USER}
 * <p/>
 * 版本: 1.0
 * <p/>
 * <p/>
 * 创建日期 : ${DATE}  ${HOUR}:${MINUTE}
 * <p/>
 * <p/>
 * 邮箱：Studylifetime@sina.com
 * <p/>
 * 描述:
 * <p/>
 * <p/>
 * 修订历史:
 * <p/>
 * =========================================================
 */
public class DiskCache implements ImageCache {
    Context mContext;
    String mDiskURL = "";

    public DiskCache(Context context) {

        mContext = context;

        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ToastUtils.showToast(context, "SDcard异常");
        } else {
            mDiskURL = Environment.getExternalStorageDirectory().getPath() + File.separator;
        }

    }


    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            File file = new File(mDiskURL + url);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fos);
        }
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(mDiskURL + url);
    }
}

package com.yangzheandroid.retrofitutils.image.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Author：Jalen on 2016/8/22 23:30
 * Contact: studylifetime@sina.com
 */
public class DoubleCache implements ImageCache {
    Context mContext;

    public DoubleCache(Context context) {
        mContext = context;
    }

    MemoryCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache(mContext);

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
}

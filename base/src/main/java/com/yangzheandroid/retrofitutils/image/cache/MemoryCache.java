package com.yangzheandroid.retrofitutils.image.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Author：Jalen on 2016/8/22 23:03
 * Contact: studylifetime@sina.com
 */
public class MemoryCache implements ImageCache {
    //图片缓存
    LruCache<String, Bitmap> mBitmapLruCache;

    public MemoryCache() {
        initImageLoader();
    }

    private void initImageLoader() {
        //计算可使用的内存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //取四分之一当做内存大小
        int cacheSize = maxMemory / 4;
        mBitmapLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mBitmapLruCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = mBitmapLruCache.get(url);
        return bitmap;

    }
}

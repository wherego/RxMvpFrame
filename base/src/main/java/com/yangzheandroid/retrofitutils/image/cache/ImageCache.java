package com.yangzheandroid.retrofitutils.image.cache;

import android.graphics.Bitmap;

/**
 * 缓存
 */
public interface ImageCache {

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}

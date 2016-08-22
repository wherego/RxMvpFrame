package com.yangzheandroid.retrofitutils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.yangzheandroid.retrofitutils.image.cache.ImageCache;
import com.yangzheandroid.retrofitutils.image.cache.MemoryCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * 描述:  图片的工具类
 * <p/>
 * <p/>
 * 修订历史:
 * <p/>
 * =========================================================
 */
public class ImageLoader {
    private static final String TAG = ImageLoader.class.getSimpleName();
    //默认是MemoryCache
    ImageCache mImageCache = new MemoryCache();

    //线程池存储变量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    /**
     * 加载图片的方法
     *
     * @param url
     * @param imageView
     */
    public void dispayImage(final String url, final ImageView imageView) {

        Bitmap bitmap = mImageCache.get(url);

        Log.e("----------", "============   " + bitmap);

        if (bitmap != null) {

            Log.e("----------", "从存储里边开始取了");

            imageView.setImageBitmap(bitmap);
            return;
        }

        submitImage(url, imageView);

     }

    private void submitImage(final String url, final ImageView imageView) {

        imageView.setTag(url);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = downLoadImage(url);

                if (bitmap == null) {
                    return;
                }

                if (url.equals(imageView.getTag())) {
                    imageView.setImageBitmap(bitmap);
                }

                mImageCache.put(url, bitmap);

            }
        });
    }

    /**
     * 网络请求图片
     *
     * @param bitmapurl
     * @return
     */
    private Bitmap downLoadImage(String bitmapurl) {

        Log.e("----------", "----------downLoadImage----------");
        Bitmap bitmap = null;
        try {
            URL url = new URL(bitmapurl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(http.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }


}

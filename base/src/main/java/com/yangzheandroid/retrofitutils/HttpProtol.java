package com.yangzheandroid.retrofitutils;


import com.yangzheandroid.retrofitutils.base.Constant;
import com.yangzheandroid.retrofitutils.okhttp.BuilerConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：SEELE on 2016/7/21 17:28
 * Contact: studylifetime@sina.com
 */
public class HttpProtol {

    public static Retrofit retrofit = null;

    private static Retrofit.Builder sBuilder;

    private static HttpProtol sHttpProtol;

    private static BuilerConfig sBuilerConfig;

    private HttpProtol() {
    }



    /**
     * 获得正在BaseURL的地址
     *
     * @return
     */
    public static HttpProtol newBuilder() {
        if (retrofit == null) {
            sBuilerConfig = BuilerConfig.newBuilder()
                    .addDownLoadInterceptor()
                    .setConnectTimeout(10, TimeUnit.SECONDS)
                    .setReadTimeout(10, TimeUnit.SECONDS)
                    .setWriteTimeout(10, TimeUnit.SECONDS)
                    .addParam("uutype","2")
//                    .addParam("version","1.0.0") // TODO: 2016/8/6 需要以后确定
//                    .addParam("uuid","")  // TODO: 2016/8/6 需要以后确定
                    .retryOnConnectionFailure(true);

            sBuilder = new Retrofit.Builder()
                    .baseUrl(Constant.BaseURL)
                    .client(sBuilerConfig.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }

        return sHttpProtol;
    }

    /**
     * 最后的返回的数字
     *
     * @return
     */
    public static Retrofit build() {
        return sBuilder.build();
    }

    /**
     * 获得特殊BaseURL的Retrofit的方法
     *
     * @return
     */
    public static HttpProtol baseUrl(String NewURL) {

        sBuilder = sBuilder.baseUrl(NewURL);

        return sHttpProtol;
    }

    /**
     * 设置失败后是否重连
     *
     * @return
     */
    public static HttpProtol retryOnConnectionFailure(boolean flag) {

        sBuilerConfig = sBuilerConfig.retryOnConnectionFailure(flag);

        sBuilder.client(sBuilerConfig.build());

        return sHttpProtol;
    }

    /**
     * 添加多个Header
     *
     * @return
     */
    public static HttpProtol addHeaderMap(Map<String, String> HeaerMap) {

        sBuilerConfig = sBuilerConfig.addHeaderMap(HeaerMap);

        sBuilder.client(sBuilerConfig.build());

        return sHttpProtol;
    }


    /**
     * 添加多个Header
     *
     * @return
     */
    public static HttpProtol addHeader(String key, String value) {

        sBuilerConfig = sBuilerConfig.addHeader(key, value);

        sBuilder.client(sBuilerConfig.build());

        return sHttpProtol;
    }


    /**
     * 添加多个Param
     *
     * @return
     */
    public static HttpProtol addParamMap(Map<String, String> HeaerMap) {

        sBuilerConfig = sBuilerConfig.addParamMap(HeaerMap);

        sBuilder.client(sBuilerConfig.build());

        return sHttpProtol;
    }


    /**
     * 添加一个Param
     *
     * @return
     */
    public static HttpProtol addParam(String key, String value) {

        sBuilerConfig = sBuilerConfig.addParam(key, value);

        sBuilder.client(sBuilerConfig.build());

        return sHttpProtol;
    }
}

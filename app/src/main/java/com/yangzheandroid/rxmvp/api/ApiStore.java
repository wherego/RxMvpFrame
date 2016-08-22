package com.yangzheandroid.rxmvp.api;


import com.yangzheandroid.rxmvp.beans.BaseCallModel;
import com.yangzheandroid.rxmvp.beans.RoomBean;
import com.yangzheandroid.rxmvp.beans.UserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author：SEELE on 2016/8/2 11:15
 * Contact: studylifetime@sina.com
 */
public interface ApiStore {
    /**
     * 下载的方法
     *
     * @param downurl
     * @return
     */
    // TODO: 2016/8/5 这是测试的下载的方法,以后需要在修改
    @GET("{downurl}")
    Call<ResponseBody> loadFile(@Path("downurl") String downurl);

    /**
     * 登录的接口方法
     *
     * @param account 账号
     * @param passwd  密码
     * @return
     */
    @POST("/service/member/main/login")
    Observable<BaseCallModel<UserBean>> login(@Query("account") String account, @Query("passwd") String passwd);

    /**
     * 注册的接口
     *
     * @param account
     * @param passwd
     * @param auth
     * @return
     */
    @POST("/service/member/main/reg")
    Observable<BaseCallModel<UserBean>> register(@Query("account") String account, @Query("passwd") String passwd, @Query("auth") String auth);

    /**
     * 创建房间的接口
     *
     * @param token
     * @param liveName
     * @param liveInfo
     * @param liveArea
     * @return
     */
    @POST("/service/my/live/create")
    Observable<BaseCallModel<RoomBean>> createRoom(@Query("token") String token, @Query("liveName") String liveName, @Query("liveInfo") String liveInfo, @Query("liveArea") String liveArea);

    /**
     * 检查token是否过期的接口
     *
     * @param token
     * @return
     */
    @POST("/service/sys/token/check")
    Observable<BaseCallModel<UserBean>> checkToken(@Query("token") String token);


    /**
     * 修改密码的接口
     *
     * @param token
     * @param oldPasswd
     * @param passwd
     * @return
     */
    @POST("/service/member/main/update_passwd")
    Observable<BaseCallModel<UserBean>> modifyUserPsd(@Query("token") String token, @Query("oldPasswd") String oldPasswd, @Query("passwd") String passwd);
}

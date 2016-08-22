package com.yangzheandroid.retrofitutils.exception;

/**
 * Author：SEELE on 2016/7/22 15:48
 * Contact: studylifetime@sina.com
 */
public class ServerException extends Throwable {

    public ServerException(String message) throws Exception {
        throw new Exception(message);
    }
}

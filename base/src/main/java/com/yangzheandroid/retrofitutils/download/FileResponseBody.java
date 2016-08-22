package com.yangzheandroid.retrofitutils.download;


import com.yangzheandroid.retrofitutils.beans.FileLoadingBean;
import com.yangzheandroid.retrofitutils.rx.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * Author：SEELE on 2016/7/25 14:46
 * Contact: studylifetime@sina.com
 */
public class FileResponseBody extends ResponseBody {

    Response originalResponse;

    public FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    @Override
    public long contentLength() {// 返回文件的总长度，也就是进度条的max
        return originalResponse.body().contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesReaded = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                // 通过RxBus发布进度信息
                RxBus.getDefault().post(new FileLoadingBean(bytesReaded, contentLength()));

                return bytesRead;
            }
        });
    }
}

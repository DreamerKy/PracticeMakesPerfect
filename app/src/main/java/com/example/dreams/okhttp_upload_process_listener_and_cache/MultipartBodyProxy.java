package com.example.dreams.okhttp_upload_process_listener_and_cache;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * Created by likaiyu on 2020/4/26.
 * 由于MultipartBody是final类型，所以无法继承，需要监听上传进度就得借助静态代理
 */
public class MultipartBodyProxy extends RequestBody {

    RequestBody requestBody;
    private int mCurrentLength = 0;
    private UpdateProcessListener processListener;

    public MultipartBodyProxy(RequestBody requestBody, UpdateProcessListener processListener) {
        this.requestBody = requestBody;
        this.processListener = processListener;
    }


    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        //数据长度
        final long contentLength = contentLength();
        //sink代理
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                //每次写都会进到这里
                mCurrentLength += byteCount;
                if (processListener != null) {
                    processListener.onProcess(contentLength, mCurrentLength);
                }
                super.write(source, byteCount);
            }
        };

        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }
}

package com.example.dreams.networkframework;

/**
 * Created by likaiyu on 2019/10/28.
 */
public interface IHttpRequest {
    void setUrl(String url);

    void setData(byte[] data);

    /**
     * 两个接口拼在一起
     * @param listener
     */
    void setListener(CallBackListener listener);

    void execute();

}

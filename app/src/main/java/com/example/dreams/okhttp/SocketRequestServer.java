package com.example.dreams.okhttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by likaiyu on 2020/1/22.
 * 主要用于组装请求信息
 */
public class SocketRequestServer {

    private final String K = " ";
    private final String VIERSION = "HTTP/1.1";
    private final String GRGN = "\r\n";

    //通过Request对象寻找域名HOST

    public String getHost(Request request){
        try {
            URL url = new URL(request.getUrl());
            return url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPort(Request request) {
        try {
            URL url = new URL(request.getUrl());
            int port = url.getPort();
            return port == -1 ? url.getDefaultPort() : port;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getRequestHeaderAll(Request request){
        //得到请求方式
        URL url = null;
        try {
            url = new URL(request.getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String file = url.getFile();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request.getRequestMethod())
                .append(K)
                .append(file)
                .append(K)
                .append(VIERSION)
                .append(GRGN);

        //获取请求集
        if(!request.getmHeaderList().isEmpty()){
            Map<String,String> mapList = request.getmHeaderList();
            for(Map.Entry<String,String> entry:mapList.entrySet()){
                stringBuffer.append(entry.getKey())
                        .append(":")
                        .append(K)
                        .append(entry.getValue())
                        .append(GRGN);
            }
            //拼接空行，代表下面的POST请求体了
            stringBuffer.append(GRGN);
        }
        if("POST".equalsIgnoreCase(request.getRequestMethod())){
            stringBuffer.append(request.getRequestBody().getBody())
                    .append(GRGN);
        }
        return stringBuffer.toString();

    }





}

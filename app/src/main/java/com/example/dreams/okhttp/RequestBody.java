package com.example.dreams.okhttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/1/22.
 */
public class RequestBody {

    public static final String TYPE = "application/x-www-form-urlencoded";
    private final String ENC = "utf-8";
    Map<String ,String> bodys = new HashMap<>();

    //添加请求体信息
    public void addBody(String key,String value){
        try{
            bodys.put(URLEncoder.encode(key,ENC),URLEncoder.encode(value,ENC));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
    //获取请求体信息
    public String getBody(){
        StringBuffer stringBUffer = new StringBuffer();
        for(Map.Entry<String,String> stringStringEntry :bodys.entrySet()){
            stringBUffer.append(stringStringEntry.getKey())
                    .append("=")
                    .append(stringStringEntry.getValue())
                    .append("&");
        }
        if(stringBUffer.length()!=0){
            stringBUffer.deleteCharAt(stringBUffer.length()-1);
        }
        return stringBUffer.toString();
    }


}

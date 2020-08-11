package com.example.dreams.factory;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class PropertiesFactory {
    public static Api createApi(Context context) throws IOException {
        Properties properties = new Properties();
        //放在app/src/main/assets
        InputStream inputStream = context.getAssets().open("config.properties");
        properties.load(inputStream);
        try {
            Class<?> creat_a = Class.forName(properties.getProperty("creat_a"));
            Api o = (Api) creat_a.newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //放在app/src/main/res/raw
//        InputStream inputStream1 = context.getResources().openRawResource("config.properties");
        //java写法
        InputStream inputStream2 = PropertiesFactory.class.getResourceAsStream("assets/config.properties");
        return null;
    }

    public static BufferedReader getBufferedReader(String file) {
        try {
            //文件字节流
            FileInputStream fileInputStream = new FileInputStream(file);
            //字节读取流
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            //缓冲字节流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();

            return bufferedReader;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}

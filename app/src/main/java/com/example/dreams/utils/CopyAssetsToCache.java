package com.example.dreams.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by likaiyu on 2019/12/25.
 */
public class CopyAssetsToCache {

    /**
     * 从Assets中将插件apk拷贝到app缓存目录,返回文件缓存路径
     * @return
     */
    public static String copyAssetsToCache(Context context, String fileName){
        File mCacheDir = context.getCacheDir();
        if(!mCacheDir.exists()){
            mCacheDir.mkdir();
        }

        File outPutPath = new File(mCacheDir,fileName);
        //因为要拷贝文件到指定目录，所以如果源目录有文件那就删除
        if(outPutPath.exists()){
            outPutPath.delete();
        }

        InputStream is = null;//输入流
        FileOutputStream fos = null;//输出流

        //创建新文件
        try {
            boolean createSuccess = outPutPath.createNewFile();
            if(createSuccess){//如果创建成功
                //获取输入流
                is = context.getAssets().open(fileName);
                //创建输入流
                fos = new FileOutputStream(outPutPath);
                //缓冲
                byte[] buffer = new byte[is.available()];
                int length;
                while((length = is.read(buffer)) != -1){
                    //开始写入数据
                    fos.write(buffer,0,length);
                }
                //返回缓存文件路径
                return outPutPath.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //刷新此输出流并强制写出所有缓冲的输出字节
                fos.flush();
                //关闭此流并释放所有相关的系统资源
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;


//        File cacheDir = context.getCacheDir();
//        if(!cacheDir.exists()){
//            cacheDir.mkdirs();
//        }
//        File outPath = new File(cacheDir,fileName);
//        if(outPath.exists()){
//            outPath.delete();
//        }
//        InputStream is = null;
//        FileOutputStream fos = null;
//
//        try {
//            //创建文件
//            boolean newFile = outPath.createNewFile();
//            if(newFile){
//                is = context.getAssets().open(fileName);
//                fos = new FileOutputStream(outPath);
//                byte[] buf = new byte[is.available()];
//                int byteCount;
//                while ((byteCount = is.read(buf))!=-1) {
//                    fos.write(buf, 0, byteCount);
//                }
//                return outPath.getAbsolutePath();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                fos.flush();
//                is.close();
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
    }

}

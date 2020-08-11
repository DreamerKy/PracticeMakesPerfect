package com.example.dreams.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.dreams.net.MApplication;
import com.example.dreams.utils.Reflector;

import java.io.File;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;

/**
 * Created by likaiyu on 2019/12/29.
 */
public class DexElementFuse {
    private Resources resources = null;
    private AssetManager assetManager = null;

    public void mainPluginFuse(Context mContext)throws Exception{
        //宿主的dexElements
        Object mDexPathList = Reflector.on("dalvik.system.BaseDexClassLoader").field("pathList").get(mContext.getClassLoader());
        Object[] mMainDexElements = Reflector.on(mDexPathList.getClass()).field("dexElements").get(mDexPathList);

        //插件dexElements,从Apk包中找
        File fileDir = mContext.getDir("pDir", Context.MODE_PRIVATE);
        DexClassLoader dexClassLoader = new DexClassLoader(MApplication.pluginPath,fileDir.getAbsolutePath(),null,mContext.getClassLoader());
        Object mPluginDexPathList = Reflector.on("dalvik.system.BaseDexClassLoader").field("pathList").get(dexClassLoader);
        Object[] mPluginDexElements = Reflector.on(mPluginDexPathList.getClass()).field("dexElements").get(mPluginDexPathList);
        System.out.println("mPluginDexElements 长度 -- "+mPluginDexElements.length);

        //创建新的DexElements
        Object[] finalDexElements = (Object[]) Array.newInstance(mMainDexElements.getClass().getComponentType(), mMainDexElements.length + mPluginDexElements.length);
        //合并数组
        System.arraycopy(mPluginDexElements,0,finalDexElements,0,mPluginDexElements.length);
        System.arraycopy(mMainDexElements,0,finalDexElements,mPluginDexElements.length,mMainDexElements.length);
        //设置到宿主的pathList中去
        Reflector.on(mDexPathList.getClass()).field("dexElements").set(mDexPathList,finalDexElements);
        Resources r = mContext.getResources();
        assetManager = AssetManager.class.newInstance();
        Reflector.on(AssetManager.class).method("addAssetPath").call(assetManager, MApplication.pluginPath);
        resources = new Resources(assetManager,r.getDisplayMetrics(),r.getConfiguration());
    }


    public Resources getResources() {
        return resources;
    }


}

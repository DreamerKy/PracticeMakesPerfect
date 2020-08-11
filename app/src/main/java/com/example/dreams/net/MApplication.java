package com.example.dreams.net;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import com.cardinfo.mobile.blockmonitor.FSBlockMonitor;
import com.example.dreams.launchertask.TaskDispatcher;
import com.example.dreams.plugin.DexElementFuse2;
import com.example.dreams.plugin.Parameter;
import com.example.dreams.utils.CopyAssetsToCache;
import com.example.dreams.widget.DebugIcon;
import com.kotlin.vip.skinlibrary.SkinManager;

import java.util.concurrent.CountDownLatch;

/**
 * Created by likaiyu on 2019/10/24.
 */
public class MApplication extends Application {

    private String mDeviceId;
    public static String pluginPath;
    public static Context context;
    private DexElementFuse2 dexElementFuse = new DexElementFuse2();
    CountDownLatch mCountDownLatch = new CountDownLatch(1);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Debug.startMethodTracing("App");
//        TraceCompat.beginSection("onCreate");
        httpHelperInit();
        taskDispatcherInit();
        skinManagerInit();
        registerActivityLifecycle();
        context = this;
//        FSBlockMonitor.start();

        //-----------HOOK START--------------//
        //从Assest目录中获取插件路径
        pluginPath = CopyAssetsToCache.copyAssetsToCache(this, Parameter.PLUGIN_FILE_NAME);
        System.out.println("pulginPath -- " + pluginPath);
        //Hook AMS
        try {
//            AMSCheckEngine.hookAMS(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Hook mH
        try {
//            ActivityThreadmHRestore.mActivityThreadmHAction(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Dex融合
        try {
//            dexElementFuse.mainPluginFuse(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //-----------HOOK END--------------//

//        TraceCompat.endSection();
//        Debug.stopMethodTracing();
        try {
            //如果CountDownLatch未被满足，则一直等着
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(ActivityLifecycleImpl.instance);
    }

    private void skinManagerInit() {
        SkinManager.init(this);
    }

    private void taskDispatcherInit() {
        TaskDispatcher.init(this);
        TaskDispatcher taskDispatcher = TaskDispatcher.createInstance();

    }

    private void httpHelperInit() {
        HttpHelper.init(new VolleyProcesser(this));
        //满足CountDownLatch
        mCountDownLatch.countDown();
    }
//
//    @Override
//    public Resources getResources() {
//        return dexElementFuse.getResources() == null ? super.getResources() : dexElementFuse.getResources();
//    }

    public String getmDeviceId() {
        return mDeviceId;
    }

    public void setmDeviceId(String mDeviceId) {
        this.mDeviceId = mDeviceId;
    }


    static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {

        private static ActivityLifecycleImpl instance = new ActivityLifecycleImpl();

        private int mConfigCount = 0;
        private ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            System.out.println("onActivityCreated -- " + activity.getComponentName());
        }


        @Override
        public void onActivityStarted(Activity activity) {
            System.out.println("onActivityStarted-- " + activity.getComponentName());
            if (mConfigCount < 0) {
                ++mConfigCount;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            System.out.println("onActivityResumed-- " + activity.getComponentName());
//            ((ViewGroup) activity.findViewById(android.R.id.content)).addView(DebugIcon.getInstance(), mParams);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            System.out.println("onActivityPaused-- " + activity.getComponentName());
//            ((ViewGroup) activity.findViewById(android.R.id.content)).removeView(DebugIcon.getInstance());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            System.out.println("onActivityStopped-- " + activity.getComponentName());
            if (activity.isChangingConfigurations()) {
                --mConfigCount;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            

        }
    }

}

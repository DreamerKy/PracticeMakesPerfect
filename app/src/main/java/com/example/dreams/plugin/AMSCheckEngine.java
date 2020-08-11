package com.example.dreams.plugin;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by likaiyu on 2019/12/25.
 */
public class AMSCheckEngine {
    //26以上系统启动Acitivty调用 ActivityManager.getService().startActivity
    //ActivityManager.getService()获取到的是AMS在应用进程的Binder代理，实现自IActivityManager
    //


//    protected void hookSystemServices() {
//        try {
//            Singleton<IActivityManager> defaultSingleton;
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                defaultSingleton = Reflector.on(ActivityManager.class).field("IActivityManagerSingleton").get();
//            } else {
//                defaultSingleton = Reflector.on(ActivityManagerNative.class).field("gDefault").get();
//            }
//            IActivityManager origin = defaultSingleton.get();
//            IActivityManager activityManagerProxy = (IActivityManager) Proxy.newProxyInstance(mContext.getClassLoader(), new Class[] { IActivityManager.class },
//                    createActivityManagerProxy(origin));
//
//            // Hook IActivityManager from ActivityManagerNative
//            Reflector.with(defaultSingleton).field("mInstance").set(activityManagerProxy);
//
//            if (defaultSingleton.get() == activityManagerProxy) {
//                this.mActivityManager = activityManagerProxy;
//                Log.d(TAG, "hookSystemServices succeed : " + mActivityManager);
//            }
//        } catch (Exception e) {
//            Log.w(TAG, e);
//        }
//    }


    public static void hookAMS(final Context context) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        Object mIActivityManager = null;
        Object mIActivityManagerSingleton = null;
        if (AndroidSdkVersion.isAndroidOS_26_27_28()) {
            //startActivity使用的是AIDL
            Class mActivityManagerClass = Class.forName("android.app.ActivityManager");
            mIActivityManager = mActivityManagerClass.getMethod("getService").invoke(null);
            Field iActivityManagerSingletonField = mActivityManagerClass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            mIActivityManagerSingleton = iActivityManagerSingletonField.get(null);
        } else if (AndroidSdkVersion.isAndroidOS_21_22_23_24_25()) {
            Class mActivityManagerClass = Class.forName("android.app.ActivityManagerNative");
            Method getDefaultMethod = mActivityManagerClass.getMethod("getDefault");
            getDefaultMethod.setAccessible(true);
            mIActivityManager = getDefaultMethod.invoke(null);
            Field gDefaultField = mActivityManagerClass.getDeclaredField("gDefault");
            gDefaultField.setAccessible(true);
            mIActivityManagerSingleton = gDefaultField.get(null);
        }

        //动态代理的接口
        Class mIActivityManagerInterface = Class.forName("android.app.IActivityManager");
        final Object finalMIActivityManager = mIActivityManager;
//        Object o = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mIActivityManagerInterface}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("----> " + method.getName());
//                if ("startActivity".equals(method.getName())) {
//                    Intent proxyIntent = new Intent(context, ProxyActivity.class);
//                    Intent intent = (Intent) args[2];
//                    proxyIntent.putExtra(Parameter.TARGET_INTENT, intent);
//                    args[2] = proxyIntent;
//                }
//
//                //让方法继续执行
//                return method.invoke(finalMIActivityManager, args);
//            }
//        });
        Object mIActivityManagerProxy = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mIActivityManagerInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                public int startActivity(IApplicationThread caller, String callingPackage, Intent intent,
//                        String resolvedType, IBinder resultTo, String resultWho, int requestCode,
//                int startFlags, ProfilerInfo profilerInfo, Bundle options) throws RemoteException {}
                    System.out.println("----> " + method.getName());
                if ("startActivity".equals(method.getName())) {
                    //用ProxyActivity替换不能通过AMS检测的Activity,用该intent替换startActivity方法中的第三个参数
                    Intent proxyIntent = new Intent(context, ProxyActivity2.class);
                    Intent target = (Intent) args[2];
                    proxyIntent.putExtra(Parameter.TARGET_INTENT, target);
                    args[2] = proxyIntent;
                }
                //让方法继续执行
                return method.invoke(finalMIActivityManager, args);
            }
        });

        if(mIActivityManagerSingleton == null || mIActivityManagerProxy == null){
            throw new IllegalAccessException("未检测到版本系统。。");
        }

        //Hook点---"android.util.Singleton" 中的成员变量"mInstance"
        //我们的最终目的是用我们的动态代理对象换掉IActivityManager（系统通过AIDL生成的Java文件）
        Class mSingletonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = mSingletonClass.getDeclaredField("mInstance");
        mInstanceField.setAccessible(true);
        //该变量设置成我们的代理对象
        mInstanceField.set(mIActivityManagerSingleton, mIActivityManagerProxy);

    }

}

package com.example.dreams.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.dreams.utils.Reflector;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by likaiyu on 2019/12/28.
 */
public class ActivityThreadmHRestore {

    public static void mActivityThreadmHAction(Context mContext) throws Exception {
        if (AndroidSdkVersion.isAndroidOS_28()) {
            mHRestore(new Custom_28_CallBack());
        } else if (!AndroidSdkVersion.isAndroidOS_28()) {
            mHRestore(new Custom_21TO27_Callback());
        } else {
            throw new IllegalStateException("未支持版本");
        }
    }

    private static void mHRestore(Handler.Callback callBack) throws Exception {
        //最终要给ActivityThread中的mH设置一个自定义CallBack,
        //在回调handleMessage之前进入我们自定义的CallBack中执行
        //我们自己的逻辑---将ProxyActivity替换回我们的plugin中的Activity

//        Class mActivityThreadClass = Class.forName("android.app.ActivityThread");
//        Object mActivityThread = mActivityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null);
//        Field mHField = mActivityThreadClass.getDeclaredField("mH");
//        mHField.setAccessible(true);
//        Object mHObject = mHField.get(mActivityThread);
////        mHObject.getClass().getDeclaredField("mCallback");
//        Field mCallbackField = Handler.class.getDeclaredField("mCallback");
//        mCallbackField.setAccessible(true);
//        mCallbackField.set(mHObject, callBack);

        //工具类封装反射
        Class mActivityThreadClass = Class.forName("android.app.ActivityThread");
        Object mActivityThread = Reflector.on(mActivityThreadClass).method("currentActivityThread").call();
        Object mH = Reflector.on(mActivityThreadClass).field("mH").get(mActivityThread);
        Reflector.on(mH.getClass()).field("mCallback").set(mH, callBack);
    }

    private static class Custom_28_CallBack implements Handler.Callback {


        @Override
        public boolean handleMessage(Message msg) {
            //final ClientTransaction transaction = (ClientTraction)mag.obj;
            //mTransactionExecutor.execute(transaction);
            if (Parameter.EXECUTE_TRANSACTION == msg.what) {
                Object mClientTraction = msg.obj;
                try {
                    Class<?> mClientTransactionClass = Class.forName("android.app.servertransaction.ClientTransaction");
                    Field mActivityCallbacksField = mClientTransactionClass.getDeclaredField("mActivityCallbacks");
                    mActivityCallbacksField.setAccessible(true);
                    List mActivityCallBacks = (List) mActivityCallbacksField.get(mClientTraction);
                    if (mActivityCallBacks.size() == 0) {
                        return false;
                    }
                    //其实是LaunchActivityItem，继承自ClientTransactionItem
                    Object mLaunchActivityItem = mActivityCallBacks.get(0);
                    Class<?> mLaunchActivityItemClass = Class.forName("android.app.servertransaction.LaunchActivityItem");
                    if (!mLaunchActivityItemClass.isInstance(mLaunchActivityItem)) {
                        return false;
                    }
                    Field mIntentField = mLaunchActivityItemClass.getDeclaredField("mIntent");
                    mIntentField.setAccessible(true);
                    Intent proxyIntent = (Intent) mIntentField.get(mLaunchActivityItem);
                    Intent targetIntent = proxyIntent.getParcelableExtra(Parameter.TARGET_INTENT);
                    if (targetIntent != null) {
                        mIntentField.set(mLaunchActivityItem, targetIntent);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return false;
        }
    }

    private static class Custom_21TO27_Callback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            if (Parameter.LAUNCH_ACTIVITY == msg.what) {
                Object mActivityClientRecord = msg.obj;
                try {
                    Field intentField = mActivityClientRecord.getClass().getDeclaredField("intent");
                    intentField.setAccessible(true);
                    Intent proxyIntent = (Intent) intentField.get(mActivityClientRecord);
                    Intent targetIntent = proxyIntent.getParcelableExtra(Parameter.TARGET_INTENT);
                    if (targetIntent != null) {
                        intentField.set(mActivityClientRecord, targetIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }
}

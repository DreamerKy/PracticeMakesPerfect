package com.kotlin.vip.arouterapi;

import android.content.Context;
import android.os.Bundle;

/**
 * Bundle拼接参数管理类
 */
public final class BundleManager {

    private Bundle bundle = new Bundle();
    // 底层业务接口
    private Call call;
    // 是否回调setResult()
    private boolean isResult;

    Bundle getBundle() {
        return bundle;
    }

    Call getCall() {
        return call;
    }

    void setCall(Call call) {
        this.call = call;
    }

    boolean isResult() {
        return isResult;
    }

    // 不允许传null，可以传null
    public BundleManager withString(String key, String value) {
        bundle.putString(key, value);
        return this;
    }

    // 示例代码，需要拓展
    public BundleManager withResultString( String key,  String value) {
        bundle.putString(key, value);
        isResult = true;
        return this;
    }

    public BundleManager withBoolean( String key, boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }

    public BundleManager withInt( String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public BundleManager withBundle( Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public Object navigation(Context context) {
        return RouterManager.getInstance().navigation(context, this, -1);
    }

    // 这里的code，可能是requestCode，也可能是resultCode。取决于isResult
    public Object navigation(Context context, int code) {
        return RouterManager.getInstance().navigation(context, this, code);
    }
}

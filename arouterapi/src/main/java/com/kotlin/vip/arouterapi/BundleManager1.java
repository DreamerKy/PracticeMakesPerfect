package com.kotlin.vip.arouterapi;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by likaiyu on 2019/12/12.
 */
public final class BundleManager1 {
    private Bundle bundle = new Bundle();
    private Call call;
    private boolean isResult;

    public Bundle getBundle() {
        return bundle;
    }

    public boolean isResult() {
        return isResult;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public Object getCall() {
        return call;
    }

    public BundleManager1 withString( String key,  String value){
        bundle.putString(key,value);
        return this;
    }

    public BundleManager1 withResultString( String key,  String value){
        isResult = true;
        bundle.putString(key,value);
        return this;
    }

    public BundleManager1 withBundle( Bundle bundle){
        this.bundle = bundle;
        return this;
    }

    public Object navigation(Context context){
        return RouterManager1.getInstance().navigation(context,this,-1);
    }

    public Object navigation(Context context,int code){
        return RouterManager1.getInstance().navigation(context,this,code);
    }
}

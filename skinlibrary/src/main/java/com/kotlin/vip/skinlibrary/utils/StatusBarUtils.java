package com.kotlin.vip.skinlibrary.utils;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;

/**
 * Created by likaiyu on 2019/12/2.
 */
public class StatusBarUtils {
    public static void forStatusBar(Activity activity){

        TypedArray a = activity.getTheme().obtainStyledAttributes(0,new int[]{
                android.R.attr.statusBarColor
        });

        int color = a.getColor(0,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
        }
        a.recycle();
    }

    public static void forStatusBar(Activity activity, int skinColor){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(skinColor);
        }

    }

}

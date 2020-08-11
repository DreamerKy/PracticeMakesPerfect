package com.kotlin.vip.skinlibrary.utils;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by likaiyu on 2019/12/2.
 */
public class ActionBarUtils {
    public static void forActionBar(AppCompatActivity appCompatActivity) {
        TypedArray a = appCompatActivity.getTheme().obtainStyledAttributes(0, new int[]{
                android.R.attr.colorPrimary
        });
        int color = a.getColor(0, 0);
        a.recycle();
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    public static void forActionBar(AppCompatActivity appCompatActivity, int skinColor) {
        ActionBar acitonBar = appCompatActivity.getSupportActionBar();
        if (acitonBar != null) {
            acitonBar.setBackgroundDrawable(new ColorDrawable(skinColor));
        }
    }


}

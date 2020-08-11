package com.example.dreams.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;


/**
 * Created by likaiyu on 2019/7/19.
 */

@SuppressLint("AppCompatCustomView")
public class AutoMarqueeTextView extends TextView {
    public AutoMarqueeTextView(Context context) {
        super(context);
        setFocusable(true);
    }

    public AutoMarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
    }

    public AutoMarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
    }

    @Override
    public boolean isFocused() {
        //这个方法必须返回true，制造假象，当系统调用该方法的时候，会一直以为TextView已经获取了焦点
        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        //这个方法必须删除其方法体内的实现，也就是让他空实现，也就是说，TextView的焦点获取状态永远都不会改变
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int width = getWidth();
        int screenWidth = getScreenWidth();

    }


    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}

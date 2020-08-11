package com.kotlin.vip.viewtouchevent;

import com.kotlin.vip.viewtouchevent.listener.OnClickListener;
import com.kotlin.vip.viewtouchevent.listener.OnTouchListener;

/**
 * Created by likaiyu on 2019/10/27.
 */
public class View {
    private int left;
    private int top;
    private int right;
    private int bottom;

    private OnTouchListener mOnTouchListener;
    private OnClickListener mOnClickListener;

    View(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    void setOnTouchListener(OnTouchListener mOnTouchListener) {
        this.mOnTouchListener = mOnTouchListener;
    }

    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("view  dispatchTouchEvent ");
        boolean result = false;
        if (mOnTouchListener != null && mOnTouchListener.onTouch(this, ev)) {
            result = true;
        }
        if (!result && onTouchEvent(ev)) {
            result = true;
        }

        return result;
    }

    private boolean onTouchEvent(MotionEvent ev) {
        if (mOnClickListener != null) {
            mOnClickListener.onClickListener(this);
            return true;
        }
        return false;

    }


    public boolean isContainer(int x, int y) {
        return x >= left && x < right && y >= top && y < bottom;
    }
}

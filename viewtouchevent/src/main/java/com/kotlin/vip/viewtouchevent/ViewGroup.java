package com.kotlin.vip.viewtouchevent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2019/10/27.
 */
public class ViewGroup extends View {
    List<View> childList = new ArrayList<>();
    private View[] mChildren = new View[0];
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                '}';
    }

    private TouchTarget mFirstTouchTarget;//内存缓存

    ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    void addView(View view) {
        if (view == null) {
            return;
        }
        childList.add(view);
        mChildren = childList.toArray(new View[childList.size()]);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(name + "  dispatchTouchEvent ");

        boolean handled = false;
        boolean intercepted = onInterceptTouchEvent(ev);
        TouchTarget newTouchTarget = null;
        int actionMasked = ev.getActionMasked();
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                final View[] children = mChildren;
                for (int i = children.length - 1; i >= 0; i--) {
                    View child = children[i];
                    if (!child.isContainer(ev.getX(), ev.getY())) {
                        continue;
                    }
                    //找到能够接收事件的child分发给他
                    if (dispatchTransFormedTouchEvent(ev, child)) {
                        //采取链式结构
                        newTouchTarget = addTouchTarget(child);
                        handled = true;
                        break;
                    }
                }
            }
            //没有人接收事件
            if (mFirstTouchTarget == null) {
                handled = dispatchTransFormedTouchEvent(ev, null);
            }
        }

        return handled;
    }

    private TouchTarget addTouchTarget(View child) {
        final TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        public View child;
        public static TouchTarget sRecycleBin;
        public TouchTarget next;
        private static final Object sRecycleLock = new Object();
        private static int sRecycledCount;

        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (sRecycleLock) {
                if (sRecycleBin == null) {
                    target = new TouchTarget();
                } else {
                    target = sRecycleBin;
                }
                sRecycleBin = target.next;
                sRecycledCount--;
                target.next = null;
            }
            target.child = child;
            return target;
        }

        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经被回收");
            }
            synchronized (sRecycleLock) {
                if (sRecycledCount < 32) {
                    next = sRecycleBin;
                    sRecycleBin = this;
                    sRecycledCount += 1;
                }
            }

        }

    }

    /**
     * 分发给子控件
     *
     * @param ev
     * @param child
     * @return
     */
    private boolean dispatchTransFormedTouchEvent(MotionEvent ev, View child) {
        boolean handled;
        //当前view消费了
        if (child != null) {
            handled = child.dispatchTouchEvent(ev);
        } else {
            handled = super.dispatchTouchEvent(ev);
        }

        return handled;
    }

    private boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;

    }


}

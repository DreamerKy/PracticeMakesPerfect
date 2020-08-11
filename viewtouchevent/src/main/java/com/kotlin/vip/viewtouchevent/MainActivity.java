package com.kotlin.vip.viewtouchevent;

import com.kotlin.vip.viewtouchevent.listener.OnTouchListener;

public class MainActivity {

    public static void main(String[] args) {
        ViewGroup topViewGroup = new ViewGroup(0, 0, 1080, 1920);
        topViewGroup.setName("顶级容器");

        ViewGroup secondViewGroup = new ViewGroup(0, 0, 500, 500);
        secondViewGroup.setName("第二级容器");

        View view = new View(0, 0, 200, 200);

        secondViewGroup.addView(view);
        topViewGroup.addView(secondViewGroup);

        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);

        topViewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("顶级容器的OnTouch");
                return false;
            }
        });

        secondViewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("二级容器的OnTouch");
                return false;
            }
        });

        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("View的OnTOuch");
                return false;
            }
        });

        boolean b = topViewGroup.dispatchTouchEvent(motionEvent);
    }
}

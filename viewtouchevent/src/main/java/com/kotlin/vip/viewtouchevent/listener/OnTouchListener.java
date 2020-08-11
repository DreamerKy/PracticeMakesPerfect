package com.kotlin.vip.viewtouchevent.listener;

import com.kotlin.vip.viewtouchevent.MotionEvent;
import com.kotlin.vip.viewtouchevent.View;

/**
 * Created by likaiyu on 2019/10/27.
 */
public interface OnTouchListener {

    boolean onTouch(View view, MotionEvent event);

}

package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/10/21.
 */
public class DrawingWithBezier extends View {
    private float mX;
    private float mY;
    private Paint mPaint;
    private Path mPath;

    public DrawingWithBezier(Context context) {
        this(context, null);
    }

    public DrawingWithBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingWithBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    private void touchMove(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        float previousX = mX;
        float previousY = mY;
        float dx = Math.abs(x - previousX);
        float dy = Math.abs(y - previousY);
        if (dx > 3 || dy > 3) {
            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;
            //平滑的贝塞尔曲线
            mPath.quadTo(previousX, previousY, cX, cY);
//            mPath.lineTo(x,y);
        }
        mX = x;
        mY = y;

    }

    private void touchDown(MotionEvent event) {

//        mPath.reset();
        float x = event.getX();
        float y = event.getY();
        mX = x;
        mY = y;
        mPath.moveTo(mX, mY);
    }
}

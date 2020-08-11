package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by likaiyu on 2019/10/21.
 */
public class DrawingWithBezierSurfaceView extends SurfaceView {
    private float mX;
    private float mY;
    private Context context;
    private SurfaceHolder mSurfaceHolder;
    private Canvas canvas;
    private float mCurveEndX;
    private float mCurveEndY;

    private Paint mPaint;
    private Path mPath;
    private Rect mInvalidRect;
    private boolean isDrawing;


    public DrawingWithBezierSurfaceView(Context context) {
        this(context, null);
    }

    public DrawingWithBezierSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingWithBezierSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mSurfaceHolder = this.getHolder();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
        mPath = new Path();
        mInvalidRect = new Rect();
    }

    public void drawCanvas() {
        try {
            canvas = mSurfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);
                canvas.drawPath(mPath, mPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Thread.dumpStack();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isDrawing) {
                    Rect rect = touchMove(event);
                    if (rect != null) {
                        invalidate(rect);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isDrawing) {
                    touchUp();
                    invalidate();
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void touchDown(MotionEvent event) {
        isDrawing = true;
//        mPath.reset();
        float x = event.getX();
        float y = event.getY();
        mX = x;
        mY = y;
        mPath.moveTo(x, y);
        mInvalidRect.set((int) x, (int) y, (int) x, (int) y);
        mCurveEndX = x;
        mCurveEndY = y;
    }

    private Rect touchMove(MotionEvent event) {
        Rect areaToRefresh = null;
        final float x = event.getX();
        final float y = event.getY();
        final float previousX = mX;
        final float previousY = mY;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);
        if (dx > 3 || dy > 3) {
            areaToRefresh = mInvalidRect;
            areaToRefresh.set((int) mCurveEndX, (int) mCurveEndY, (int) mCurveEndX, (int) mCurveEndY);
            float cX = mCurveEndX = (x + previousX) / 2;
            float cY = mCurveEndY = (y + previousY) / 2;
            mPath.quadTo(previousX, previousY, cX, cY);
            areaToRefresh.union((int) previousX, (int) previousY, (int) previousX, (int) previousY);
            areaToRefresh.union((int) cX, (int) cY, (int) cX, (int) cY);
            mX = x;
            mY = y;
            drawCanvas();
        }
        return areaToRefresh;
    }

    private void touchUp() {
        isDrawing = false;
    }

}

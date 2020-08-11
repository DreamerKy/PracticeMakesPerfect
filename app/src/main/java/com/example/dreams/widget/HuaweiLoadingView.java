package com.example.dreams.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2020/1/23.
 */
public class HuaweiLoadingView extends View {
    //小圆总数
    private static final int CIRCLE_COUNT = 12;
    //小圆圆心之间夹角
    private static final int DEGREE_PER_CIRCLE = 360 / CIRCLE_COUNT;
    //所有小圆半径
    private float[] mWholeCircleRadius = new float[CIRCLE_COUNT];
    //所有元颜色
    private int[] mWholeCircleColors = new int[CIRCLE_COUNT];
    //小圆最大半径
    private float mMaxCircleRadius;
    //控件大小
    private int mSize;
    //小圆颜色
    private int mColor;

    private Paint mPaint;
    private ValueAnimator mAnimator;
    private int mAnimatorValue = 0;
    //动画时长
    private long mDuration;

    public HuaweiLoadingView(Context context) {
        this(context, null);
    }

    public HuaweiLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public HuaweiLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
        initValue();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        mSize = dp2px(context, 100);
        mColor = Color.parseColor("#333333");
        mDuration = 1000;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
    }

    private void initValue() {
        float minCircleRadius = mSize / 24;
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            switch (i) {
                case 7:
                    mWholeCircleRadius[i] = minCircleRadius * 1.25f;
                    mWholeCircleColors[i] = (int) (255 * 0.7f);
                    break;
                case 8:
                    mWholeCircleRadius[i] = minCircleRadius * 1.5f;
                    mWholeCircleColors[i] = (int) (255 * 0.8f);
                    break;
                case 9:
                case 11:
                    mWholeCircleRadius[i] = minCircleRadius * 1.75f;
                    mWholeCircleColors[i] = (int) (255 * 0.9f);
                    break;
                case 10:
                    mWholeCircleRadius[i] = minCircleRadius * 2f;
                    mWholeCircleColors[i] = (int) (255 * 1f);
                    break;
                default:
                    mWholeCircleRadius[i] = minCircleRadius;
                    mWholeCircleColors[i] = (int) (255 * 0.5f);
            }
        }
        mMaxCircleRadius = minCircleRadius * 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //去除wrap_content和match_parent对控件大小的影响
        setMeasuredDimension(mSize,mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("onDraw");
        if (mSize > 0) {
            canvas.rotate(DEGREE_PER_CIRCLE * mAnimatorValue, mSize / 2, mSize / 2);
            for (int i = 0; i < CIRCLE_COUNT; i++) {
                mPaint.setAlpha(mWholeCircleColors[i]);
                canvas.drawCircle(mSize / 2, mMaxCircleRadius, mWholeCircleRadius[i], mPaint);
                canvas.rotate(DEGREE_PER_CIRCLE, mSize / 2, mSize / 2);
            }
        }

    }

    private ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mAnimatorValue = (int)animation.getAnimatedValue();
            invalidate();
        }
    };

    public void start(){
        if(mAnimator == null){
            mAnimator = ValueAnimator.ofInt(0,CIRCLE_COUNT);
            mAnimator.addUpdateListener(mUpdateListener);
            mAnimator.setDuration(mDuration);
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.start();
        }else if(!mAnimator.isStarted()){
            mAnimator.start();
        }
    }

    public void stop(){
        if(mAnimator != null){
            mAnimator.removeUpdateListener(mUpdateListener);
            mAnimator.removeAllUpdateListeners();
            mAnimator.cancel();
            mAnimator = null;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility == VISIBLE){
            start();
        }else{
            stop();
        }
    }

    private int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }


}

package com.example.dreams.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.dreams.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by likaiyu on 2020/7/15.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DegreeView extends View {
    private static final int ANGLE = 120;
    private static final float RADIUS = Utils.dp2px(150);
    private static final float LENGTH = Utils.dp2px(100);
    private static final float PADDING = Utils.dp2px(5);
    private int mark = 0;
    private int count = 20;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint ovalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path dash = new Path();
    PathDashPathEffect effect;
    private ValueAnimator valueAnimator;
    //动画时长

    public DegreeView(Context context) {
        this(context, null);
    }

    public DegreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DegreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
        ovalPaint.setStyle(Paint.Style.FILL);
        ovalPaint.setColor(Color.RED);
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / count, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画弧
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        //画刻度
        paint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(mark))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(mark))) * LENGTH + getHeight() / 2,
                paint);
        canvas.drawOval(getWidth() / 2 - PADDING, getHeight() / 2 - PADDING, getWidth() / 2 + PADDING, getHeight() / 2 + PADDING, ovalPaint);
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

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mark = (int) animation.getAnimatedValue();
            invalidate();
            if (mark > count) {
                mark = 0;
                stop();
                start();
            }
        }
    };

    public void start() {
        if(valueAnimator == null){
            valueAnimator = ValueAnimator.ofInt(mark,count+1);
            valueAnimator.setDuration((count - mark) * 1000);
            valueAnimator.addUpdateListener(animatorUpdateListener);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.setRepeatCount(0);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.start();
        }else if(!valueAnimator.isStarted()){
            valueAnimator.start();
        }
    }

    public boolean isStart() {
        return valueAnimator != null && valueAnimator.isStarted();
    }

    public void stop(){
        if(valueAnimator != null){
            valueAnimator.removeUpdateListener(animatorUpdateListener);
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }


    int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}

package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/7/10.
 */

public class BetterProgressView extends View {

    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private int progress;
    private RectF rectF;
    private Rect rect;
    private String text = "";
    private int max = 100;

    public BetterProgressView(Context context) {
        this(context, null);
    }

    public BetterProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BetterProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(8);

        paint3 = new Paint();
        paint3.setColor(Color.RED);
        paint3.setAntiAlias(true);
        paint3.setTextSize(40);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setStrokeWidth(2);
        rectF = new RectF();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = 200;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = 200;
        }

        setMeasuredDimension(widthSize, heightSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredHeight() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2 - 2, paint);

        rectF.set(2, 2, getMeasuredWidth() - 2, getMeasuredHeight() - 2);
        canvas.drawArc(rectF, 0, 360 * progress / max, false, paint2);

        paint3.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, getMeasuredWidth() / 2 - rect.width() / 2, getMeasuredHeight() / 2 + rect.height() / 2, paint3);

    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProgessAndText(int progress, String text) {

        this.progress = progress;
        this.text = text;

        postInvalidate();
    }

}

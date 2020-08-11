package com.example.dreams.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/7/10.
 */

public class ProgressView extends View {

    private Paint paint;
    private Paint paint2;
    private int progress;
    private RectF rectF;
    private Rect rect;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (progress < 100) {
                        progress++;
                        invalidate();
                        sendEmptyMessageDelayed(0, 30);
                    }
                    break;
            }
        }
    };

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(5);
        rectF = new RectF(5, 5, 205, 205);
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
            widthSize = 300;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = 300;
        }

        setMeasuredDimension(widthSize, heightSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(105, 105, 100, paint);


        canvas.drawArc(rectF, 0, 360 * progress / 100, true, paint2);

        String text = progress + "%";
        paint.setTextSize(30);

        paint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, 100 - rect.width() / 2, 100 + rect.height() / 2, paint);

    }

    public void start() {
        progress = 0;
        handler.sendEmptyMessageDelayed(0, 30);
    }
}

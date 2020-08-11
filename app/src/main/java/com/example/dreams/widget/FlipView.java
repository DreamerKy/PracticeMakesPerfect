package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.dreams.utils.Utils;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2020/7/20.
 */

public class FlipView extends View {

    private static final float IMAGE_WIDTH = Utils.dpToPixel(200);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public FlipView(Context context) {
        this(context, null);
    }

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        camera.setLocation(0, 0, Utils.getZForCamera());
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制上半部分
        canvas.save();
        canvas.translate(getWidth() >> 1, getWidth() >> 1);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-getWidth() >> 1, -getWidth() >> 1);
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) IMAGE_WIDTH), (getWidth() - IMAGE_WIDTH) / 2, (getWidth() - IMAGE_WIDTH) / 2, paint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(getWidth() >> 1, getWidth() >> 1);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(- getWidth() >> 1, - getWidth() >> 1);
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) IMAGE_WIDTH), (getWidth() - IMAGE_WIDTH) / 2, (getWidth() - IMAGE_WIDTH) / 2, paint);
        canvas.restore();

    }
}

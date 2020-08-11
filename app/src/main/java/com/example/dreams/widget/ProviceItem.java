package com.example.dreams.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * Created by likaiyu on 2019/11/4.
 */
public class ProviceItem {
    private Path path;
    private int drawColor;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProviceItem(Path path) {
        this.path = path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setDrawColor(int drawColor) {
        this.drawColor = drawColor;
    }

    public Path getPath() {
        return path;
    }

    public int getDrawColor() {
        return drawColor;
    }

    public void drawItem(Canvas canvas, Paint mPaint, boolean selected) {
        if (selected) {
            //里面颜色
            mPaint.clearShadowLayer();
            mPaint.setStrokeWidth(1);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.BLACK);
            canvas.drawPath(path, mPaint);

            //选中时绘制描边效果
            mPaint.setStyle(Paint.Style.STROKE);
            int strokeColor = Color.WHITE;
            mPaint.setColor(strokeColor);
            canvas.drawPath(path, mPaint);
        } else {
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setShadowLayer(8, 0, 0, 0xffffff);
            canvas.drawPath(path, mPaint);

            mPaint.clearShadowLayer();
            mPaint.setColor(drawColor);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(2);
            canvas.drawPath(path, mPaint);

        }

    }

    public boolean isTouch(float x, float y) {

        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        Region region = new Region();
        region.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));
//        region.getBoundaryPath(path);
        boolean contains = region.contains((int) x, (int) y);
        return contains;
    }
}

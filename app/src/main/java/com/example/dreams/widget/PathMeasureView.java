package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.dreams.R;

/**
 * Created by likaiyu on 2019/10/21.
 */
public class PathMeasureView extends View {
    private Paint mPaint;
    private Paint mLinePaint;
    private Bitmap mBitmap;
    private Path mPath;
    private PathMeasure pathMeasure;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(6);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arror_orange);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }
        mPath.reset();
        RectF rectF = new RectF(-200f,-200f,200f,200f);
        mPath.addArc(rectF,0,90);
        canvas.drawPath(mPath, mPaint);
        mPath.addArc(rectF,180,90);
        canvas.drawPath(mPath, mPaint);
//        mPath.addCircle(0, 0, 200, Path.Direction.CW);
        pathMeasure = new PathMeasure(mPath, false);

       /* pathMeasure.getPosTan(pathMeasure.getLength() *mFloat, pos, tan);
        double degrees = Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI;
        System.out.println("degrees--" + degrees);
        matrix.reset();
        //进行角度旋转
        matrix.postRotate((float) degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //将图片绘制点中心与当前点重合
        matrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, mPaint);*/

        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat , matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        //将图片旋转坐标调整到图片中心位置
        matrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, mPaint);

        invalidate();
    }

    private Matrix matrix = new Matrix();
    private float mFloat;

}

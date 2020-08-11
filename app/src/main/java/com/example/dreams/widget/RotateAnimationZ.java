package com.example.dreams.widget;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by likaiyu on 2019/12/9.
 */
public class RotateAnimationZ extends Animation {

    int centerX, centerY;
    Camera camera = new Camera();
    public final int X = 0;
    public final int Y = 1;
    public int direction = Y;
    public boolean isZhengfangxiang = true;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        camera.save();
        if (direction == X) {
            if (isZhengfangxiang) {
                camera.rotateX(360 * interpolatedTime);
            } else {
                camera.rotateX(360 - 360 * interpolatedTime);
            }

        } else {
            if (isZhengfangxiang) {
                camera.rotateY(360 * interpolatedTime);
            } else {
                camera.rotateY(360 - 360 * interpolatedTime);
            }
        }

        //把我们的摄像头加在变换矩阵上
        camera.getMatrix(matrix);
        //设置翻转中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }
}

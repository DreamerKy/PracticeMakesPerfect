package com.example.dreams.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.dreams.R;

import java.util.concurrent.Executors;

/**
 * Created by likaiyu on 2019/10/20.
 */
public class LoadingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private enum LoadingState {
        DOWN, UP, FREE
    }

    private LoadingState loadingState = LoadingState.DOWN;
    private int ballColor;//小球颜色
    private int ballRadius;//小球半径
    private int lineColor;//连线颜色
    private int lineWidth;//连线长度
    private int strokeWidth;//绘制线宽
    private float downDistance = 0;//水平位置下降的距离
    private float maxDownDistance;//水平位置下降的距离（最低点）
    private float upDistance = 0;//从底部上弹的距离
    private float freeDownDistance = 0;//自由落体的距离
    private float maxFreeDownDistance;//自由落体的距离（最高点）

    private ValueAnimator downControl;
    private ValueAnimator upControl;
    private ValueAnimator freeDownControl;
    private AnimatorSet animatorSet;
    private boolean isAnimationShowing;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private Path path;
    private static boolean isRunning = false;//标志新线程是否在运行


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化工作
        initAttr(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        path = new Path();
        //获取holder
        holder = getHolder();
        holder.addCallback(this);
        //初始化控制器
        initControl();
    }

    private void initControl() {
        downControl = ValueAnimator.ofFloat(0, maxDownDistance);
        downControl.setDuration(500);
        downControl.setInterpolator(new DecelerateInterpolator());
        downControl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                downDistance = (float) animation.getAnimatedValue();
            }
        });
        downControl.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                loadingState = LoadingState.DOWN;
                isAnimationShowing = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }

        });

        upControl = ValueAnimator.ofFloat(0, maxDownDistance);
        upControl.setDuration(500);
        upControl.setInterpolator(new ShockInterpolator());
        upControl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                upDistance = (float) animation.getAnimatedValue();
                if (upDistance >= maxDownDistance && freeDownControl != null && !freeDownControl.isRunning() && !freeDownControl.isStarted()) {
                    freeDownControl.start();
                }

                //绳子不能超过小球底部
                if (upDistance - maxDownDistance >= freeDownDistance) {
                    upDistance = maxDownDistance + freeDownDistance;
                }

            }
        });

        upControl.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                loadingState = LoadingState.UP;
                isAnimationShowing = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

        });
        freeDownControl = ValueAnimator.ofFloat(0, (float) (2 * Math.sqrt(maxFreeDownDistance / 5)));
        freeDownControl.setDuration(700);
        freeDownControl.setInterpolator(new AccelerateInterpolator());
        freeDownControl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float t = (float) animation.getAnimatedValue();
                freeDownDistance = (float) (10 * Math.sqrt(maxFreeDownDistance / 5) * t - 5 * t * t);
            }
        });
        freeDownControl.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                loadingState = LoadingState.FREE;
                isAnimationShowing = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimationShowing = false;
                //重新开启动画
                startAllAnimator();
            }

        });
        animatorSet = new AnimatorSet();
        animatorSet.play(downControl).before(upControl);
    }

    public void startAllAnimator() {
        if (isAnimationShowing) {
            return;
        }

        if (animatorSet.isRunning()) {
            animatorSet.end();
            animatorSet.cancel();
        }
        if (freeDownControl.isRunning()) {
            freeDownControl.end();
            freeDownControl.cancel();
        }
        loadingState = LoadingState.DOWN;
        animatorSet.start();

    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        ballColor = typedArray.getColor(R.styleable.LoadingView_ball_color, Color.BLUE);
        lineColor = typedArray.getColor(R.styleable.LoadingView_line_color, Color.BLUE);
        lineWidth = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_line_width, 200);
        strokeWidth = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_stroke_width, 4);
        maxDownDistance = typedArray.getDimensionPixelSize(R.styleable.LoadingView_max_down, 50);
        maxFreeDownDistance = typedArray.getDimensionPixelSize(R.styleable.LoadingView_max_up, 50);
        ballRadius = typedArray.getDimensionPixelSize(R.styleable.LoadingView_ball_radius, 10);
        typedArray.recycle();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //创建时触发
        //开启绘制线程
        isRunning = true;
        Executors.newSingleThreadExecutor().submit(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //格式大小发生变化时触发
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //销毁
        isRunning = false;
        if (animatorSet.isRunning()) {
            animatorSet.end();
            animatorSet.cancel();
        }
        if (freeDownControl.isRunning()) {
            freeDownControl.end();
            freeDownControl.cancel();
        }
    }

    @Override
    public void run() {
        //绘制动画
        while (isRunning) {
            drawView();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void drawView() {
        try {
            if (holder != null) {
                canvas = holder.lockCanvas();
                //清屏
//                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.drawColor(0xffffffff);
                path.reset();
                //移动到贝塞尔曲线起点
                path.moveTo(getWidth() / 2f - lineWidth / 2f, getHeight() / 2f);
                if (loadingState == LoadingState.DOWN) {
                    //小球在绳子上下降
                    path.quadTo(getWidth() / 2f, 2 * downDistance + getHeight() / 2f, lineWidth/2f + getWidth() / 2f, getHeight() / 2f);
//                    path.rQuadTo(lineWidth / 2f, 2 * downDistance, lineWidth, 0);
                    paint.setColor(lineColor);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawPath(path, paint);
                    paint.setColor(calculateColor(0xfffb4748,0xff00a9fb));
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(getWidth() / 2f, getHeight() / 2 + downDistance - ballRadius - strokeWidth / 2f, ballRadius, paint);
                } else {
                    //小球在绳子上上升或自由落体
                    path.rQuadTo(lineWidth / 2f, 2 * (maxDownDistance - upDistance), lineWidth, 0);
                    paint.setColor(lineColor);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawPath(path, paint);
                    paint.setColor(calculateColor(0xfffb4748,0xff00a9fb));
                    paint.setStyle(Paint.Style.FILL);
                    if (loadingState == LoadingState.FREE) {
                        //自由落体
                        canvas.drawCircle(getWidth() / 2, getHeight() / 2f - freeDownDistance - ballRadius - strokeWidth / 2f, ballRadius, paint);
                    } else {
                        //上升
                        canvas.drawCircle(getWidth() / 2, getHeight() / 2f + (maxDownDistance - upDistance) - ballRadius - strokeWidth / 2f, ballRadius, paint);
                    }
                }
                paint.setColor(ballColor);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(getWidth() / 2f - lineWidth / 2f, getHeight() / 2f, ballRadius, paint);
                canvas.drawCircle(getWidth() / 2f + lineWidth / 2f, getHeight() / 2f, ballRadius, paint);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                assert holder != null;
                //不调用看到绘制效果
                holder.unlockCanvasAndPost(canvas);
            }
        }

    }

    private int calculateColor(int colorFrom, int colorTo) {
        float fraction = 0;
        if (loadingState == LoadingState.DOWN) {
            fraction = 0.5f + downDistance / maxDownDistance / 2f;
        } else if (loadingState == LoadingState.UP) {
            fraction = 1 - upDistance / maxDownDistance / 2f;
        } else {
            fraction = 0.5f - freeDownDistance / maxFreeDownDistance / 2;
        }
        int R = (int) (Color.red(colorFrom) + (Color.red(colorTo) - Color.red(colorFrom)) * fraction);
        int G = (int) (Color.green(colorFrom) + (Color.green(colorTo) - Color.green(colorFrom)) * fraction);
        int B = (int) (Color.blue(colorFrom) + (Color.blue(colorTo) - Color.blue(colorFrom)) * fraction);
        return Color.rgb(R, G, B);

    }

    class ShockInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            float value = (float) (1 - Math.exp(-3 * input) * Math.cos(10 * input));
            return value;
        }
    }


}

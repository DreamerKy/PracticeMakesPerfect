package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.dreams.R;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/7/13.
 * 
 */

public class SlideLockView extends View {
    private Bitmap bitmapWecat;
    private Bitmap bitmapBanner;
    private Paint paint;
    //滑块当前的左上角坐标
    private int currentX = 0;
    private int currentY = 0;

    public SlideLockView(Context context) {
        super(context);
        init();
    }

    public SlideLockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    int banner_width;
    int banner_height;
    int wechat_width;

    int left;
    int left2;
    int top;
    int top2;
    int right;

    public void init() {
        bitmapBanner = BitmapFactory.decodeResource(getResources(), R.mipmap.online_apply_card_bg);
        bitmapWecat = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_home_members_top);
        //测出图片宽高
        banner_width = bitmapBanner.getWidth();
        banner_height = bitmapBanner.getHeight();
        wechat_width = bitmapWecat.getWidth();
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //左侧点坐标
        left = (w - banner_width) / 2;
        top = (h - banner_height) / 2;
        left2 = (w - banner_width) / 2 + 100;
        top2 = (h - wechat_width) / 2;
        right = w / 2 + banner_width / 2 - wechat_width;
        currentX = left;
        currentY = top2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBanner, left, top, null);
        if (currentX < left) {
            currentX = left;
        } else if (currentX >right) {
            currentX = right;
        }
        canvas.drawBitmap(bitmapWecat, currentX, top2, null);
    }

    boolean isOnBlock;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断手指是否按到火箭上
                float downx = event.getX();
                float downy = event.getY();
                isOnBlock = isOnBlock(downx, downy);
                if (isOnBlock) {
                    Toast.makeText(getContext(), "按到了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "没按到", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnBlock) {
                    //如果按上了，获取最新坐标
                    float moveX = event.getX();
                    float moveY = event.getY();
                    currentX = (int) (moveX - wechat_width / 2);
                    currentY = (int) (moveY - wechat_width / 2);
                    //重新绘制
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //如果按上了，获取最新坐标
                float moveX = event.getX();
                float moveY = event.getY();
                currentX = (int) (moveX - wechat_width / 2);
                currentY = (int) (moveY - wechat_width / 2);
                //重新绘制
                invalidate();
                if(moveX < right){
                    currentX = left;
                }else {
                    Toast.makeText(getContext(), "解锁成功", Toast.LENGTH_SHORT).show();
                }

                break;

        }
        return true;
    }

    private boolean isOnBlock(float downx, float downy) {
        int centerX = currentX + wechat_width / 2;
        int centerY = currentY + wechat_width / 2;

        double sqrt = Math.sqrt((downx - centerX) * (downx - centerX) + (downy - centerY) * (downy - centerY));
        if (sqrt > wechat_width / 2) {
            return false;
        } else {
            return true;
        }
    }
}

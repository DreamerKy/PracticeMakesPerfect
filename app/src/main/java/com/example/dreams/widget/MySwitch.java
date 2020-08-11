package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.dreams.R;

/**
 * 自定义开关
 * 
 * @author Kevin
 * @date 2015-8-26
 */
public class MySwitch extends View {

	private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.itcast.myswitch02";

	private Paint mPaint;
	private Bitmap mBitmapBg;
	private Bitmap mBitmapSlide;
	private int mWidth;
	private int mHeight;

	private int MAX_LEFT;// 最大滑块左边距

	private int mSlideLeft;// 当前滑块左边距

	private boolean isOpen = false;// 标记当前开关状态,默认关闭

	public MySwitch(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MySwitch(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		//设置初始化状态
		isOpen = attrs.getAttributeBooleanValue(NAMESPACE, "isChecked", false);
		
		if(isOpen) {
			mSlideLeft = MAX_LEFT;
		}else {
			mSlideLeft = 0;
		}
		
		invalidate();
	}

	public MySwitch(Context context) {
		super(context);
		init();
	}

	private void init() {
		// 初始化画笔
		mPaint = new Paint();
		mPaint.setColor(Color.RED);// 画笔颜色
		// mPaint.setStyle(Style.STROKE);//空心
		mPaint.setStrokeWidth(5);// 线条粗细

		// 初始化滑块背景图片
		mBitmapBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.switch_background);
		// 初始化滑块图片
		mBitmapSlide = BitmapFactory.decodeResource(getResources(),
				R.drawable.slide_button);

		// 计算最大滑块左边距
		MAX_LEFT = mBitmapBg.getWidth() - mBitmapSlide.getWidth();

		// 设置控件的点击事件
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isClick) {
					if (isOpen) {
						// 关闭开关
						isOpen = false;
						mSlideLeft = 0;
					} else {
						// 打开开关
						isOpen = true;
						mSlideLeft = MAX_LEFT;
					}

					// 重绘界面
					invalidate();// 此方法会导致onDraw重新调用一次

					// 3. 回调方法,回调当前开关状态
					if (mListener != null) {
						mListener.onCheckChanged(MySwitch.this, isOpen);
					}
				}
			}
		});
	}

	int startX;// 滑块起始坐标
	int moveX;// 移动总距离
	boolean isClick;// 标记是否是点击

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 1. 记录当前坐标
			// event.getRawX();//基于手机屏幕的绝对坐标
			startX = (int) event.getX();// 相对于父控件的相对坐标
			break;
		case MotionEvent.ACTION_MOVE:
			// 2. 记录移动后的坐标
			int endX = (int) event.getX();

			// 3. 计算移动偏移量
			int dx = endX - startX;

			moveX += Math.abs(dx);// 记录移动总距离

			// 4. 根据偏移量, 更新滑块位置
			mSlideLeft += dx;

			// 防止滑块越界
			if (mSlideLeft < 0) {
				mSlideLeft = 0;
			}

			// 防止滑块越界
			if (mSlideLeft > MAX_LEFT) {
				mSlideLeft = MAX_LEFT;
			}

			invalidate();// 重绘

			// 5. 重新记录起始点坐标
			startX = (int) event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (moveX > 5) {// 有时候点击时会抖动一下, 所以moveX>5个像素,留一点余地
				// 移动事件
				isClick = false;
			} else {
				// 点击事件
				isClick = true;
			}

			moveX = 0;// 移动总距离归零,方便下次点击

			if (!isClick) {// 只有当前是移动状态, 才处理一下逻辑
				// 确定滑块最终位置
				if (mSlideLeft < MAX_LEFT / 2) {
					// 关闭状态
					mSlideLeft = 0;
					isOpen = false;
				} else {
					// 打开状态
					mSlideLeft = MAX_LEFT;
					isOpen = true;
				}

				invalidate();

				// 3. 回调方法,回调当前开关状态
				if (mListener != null) {
					mListener.onCheckChanged(MySwitch.this, isOpen);
				}
			}
			break;

		default:
			break;
		}

		return super.onTouchEvent(event);// 返回super的方法,可以保证onClick和onTouch同时响应
	}

	// 测量布局大小
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("onMeasure");
		// 修改大小, 改为100x100
//		 setMeasuredDimension(100, 100);
		setMeasuredDimension(mBitmapBg.getWidth(), mBitmapBg.getHeight());// 图片多大,控件就多大
//		 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// measure(测量宽高)->layout(设置位置)->draw(开始绘制)
	// onMeasure->onLayout->onDraw


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		System.out.println("onDraw");
		// 绘制一个矩形 200x200
		// canvas.drawRect(0, 0, 200, 200, mPaint);
		// 绘制一个图片, 背景图片
		canvas.drawBitmap(mBitmapBg, (mWidth - mBitmapBg.getWidth()) / 2, (mHeight - mBitmapBg.getHeight()) / 2, null);
		// 绘制滑块
		canvas.drawBitmap(mBitmapSlide, (mWidth - mBitmapBg.getWidth()) / 2 + mSlideLeft, (mHeight - mBitmapBg.getHeight()) / 2, null);
	}

	private OnCheckChangeListener mListener;

	// 1. 定义回调接口
	public interface OnCheckChangeListener {
		public void onCheckChanged(View view, boolean isChecked);
	}

	// 2. 定义设置监听的方法
	public void setOnCheckChangeListener(OnCheckChangeListener listener) {
		mListener = listener;
	}

}

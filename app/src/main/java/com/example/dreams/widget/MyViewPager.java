package com.example.dreams.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义ViewPager
 * 开发流程:
 * 1. 写一个类继承ViewGroup
 * 2. 在MainActivity中给自定义控件添加6张图片
 * 3. 重写onLayout方法,让图片一字排开
 * 4. 重写onTouchEvent, 手势识别器识别滑动事件onScroll, scrollBy进行滑动操作
 * 5. 响应手指抬起事件, 确定下一个页面, 移动到确定页面 scrollTo
 * 6. 平滑移动, Scroller, computeScroll
 * 7. 添加测试页面, 重写onMeasure方法,测量所有孩子, 保证测试页面正常显示
 * 8. 事件处理机制
 * 9. 增加RadioButton
 * 10.点击RadioButton, 切换页面
 * 11. 切换页面,更新RadioButton
 * @author Kevin
 * @date 2015-8-27
 */
public class MyViewPager extends ViewGroup {

	private GestureDetector mDetector;// 手势识别器
	private Scroller mScroller;// 滑动器

	public MyViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyViewPager(Context context) {
		super(context);
		init();
	}

	private void init() {
		mDetector = new GestureDetector(getContext(),
				new GestureDetector.SimpleOnGestureListener() {
					// 手势滑动动作
					// e1:起点, e2:终点
					// distanceX:水平距离
					// distanceY:竖直距离
					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// 整个控件滑动的方法
						scrollBy((int) distanceX, 0);// 参1:x方向偏移位置;参2:y方向偏移位置,
														// 相对位置
						// scrollTo(x, y);//绝对位移
						return super.onScroll(e1, e2, distanceX, distanceY);
					}
				});

		mScroller = new Scroller(getContext());
	}
	
	//测量布局
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);//测量viewpager的子控件的宽高
		
		//需要手动测量每一个孩子, 才能保证测试页面布局能够正常显示
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure(widthMeasureSpec, heightMeasureSpec);//此方法会将子布局中的所有控件宽高测量一遍,保证正常显示
		}

		System.out.println("widthMeasureSpec:" + widthMeasureSpec);
		System.out.println("heightMeasureSpec:" + heightMeasureSpec);

		//MeasureSpec.AT_MOST;//至多, wrap_content
		//MeasureSpec.EXACTLY;//确定的值, 宽高写死成dp, match_parent
		//MeasureSpec.UNSPECIFIED;//未确定

		int mode = MeasureSpec.getMode(widthMeasureSpec);//获取宽度模式
		int width = MeasureSpec.getSize(widthMeasureSpec);//获取宽度

		System.out.println("mode:" + mode);
		System.out.println("width:" + width);
	}

	// 设置布局位置的方法
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 需要手动设置viewpager每个子view的位置, 这样,子view才能正常在viewpager中展现
		// 保证所有item一字排开
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.layout(getWidth() * i, 0, getWidth() * (i + 1), getHeight());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDetector.onTouchEvent(event);// 将触摸事件委托给手势识别器来处理

		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			// 手指抬起
			// 判断下一个页面的位置
			int scrollX = getScrollX();// 当前滑动的水平位置
			System.out.println("scroll x:" + scrollX);
			int pos = scrollX / getWidth();
			// 多出来的位移
			int offset = scrollX % getWidth();
			if (offset > getWidth() / 2) {
				// 多出的部分已经超出屏幕一半,需要跳到下一个页面
				pos++;
			}

			System.out.println("pos:" + pos);

			// 防止超出边界
			if (pos > getChildCount() - 1) {
				pos = getChildCount() - 1;// 强制设置为最后一个item
			}

			// scrollTo(pos * getWidth(), 0);//移动到确定的位置, 瞬间切换到某个位置
			setCurrentItem(pos);
			break;

		default:
			break;
		}

		return true;
	}

	//当调用startScroll之后,此方法会不断回调, 在此方法中设置布局应该滑动的位置
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {// 判断滑动是否结束
			// 没有结束
			int currX = mScroller.getCurrX();// 获取当前滑动的x位置
			
			System.out.println("computeScroll:" + currX);
			
			scrollTo(currX, 0);// 滑动到确定的位置
			invalidate();//刷新界面
		}
	}
	
	//事件分发
	//dispatchTouchEvent->onInterceptTouchEvent->onTouchEvent
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	
	int startX;
	int startY;
	
	//事件中断的方法
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//return true;//拦截事件传递, 子控件不再响应事件, 全由Viewpager来处理事件
		//如果左右滑动,需要拦截事件
		//如果上下滑动,不需要拦截事件
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//ACTION_DOWN丢失掉了
			mDetector.onTouchEvent(ev);//将ACTION_DOWN事件转达给手势识别器, 避免丢失按下事件导致滑动的bug
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();
			
			int dx = Math.abs(endX - startX);
			int dy = Math.abs(endY - startY);
			
			if(dx>dy) {
				//左右滑动
				return true;
			}
			break;

		default:
			break;
		}
		
		return false;//不拦截事件,交给子控件(ScrollView)处理
	}

	/**
	 * 切换到某个页面
	 * @param pos
	 */
	public void setCurrentItem(int pos) {
		int distance = pos * getWidth() - getScrollX();// 计算滑动距离(目标位置减去当前位置)

		// 开始滑动, 此方法并不会立即滑动, 而是会不断的回调computeScroll方法, 需要早computeScroll方法中处理滑动位置
		mScroller.startScroll( getScrollX(), 0, distance, 0, Math.abs(distance));// 参1:起始x位置;参2:起始y位置;参3:x方向移动距离;参4:y方向移动距离;参5:移动总时间(以距离绝对值为时间,
																			// 距离越大,时间越长)
		invalidate();// 刷新界面,保证滑动正常运行
		
		if(mListener!=null) {
			mListener.onPageSeleted(pos);
		}
	}
	
	//页面切换的回调接口
	public interface OnPageChangeListener{
		public void onPageSeleted(int position);
	}
	
	private OnPageChangeListener mListener;
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		mListener = listener;
	}

}

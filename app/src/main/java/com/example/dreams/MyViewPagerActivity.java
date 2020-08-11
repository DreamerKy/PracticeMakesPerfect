package com.example.dreams;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.dreams.widget.MyViewPager;


public class MyViewPagerActivity extends Activity {

	private MyViewPager mPager;

	// 图片id集合
	private int[] mImageIds = new int[] { R.drawable.a1, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6 };

	private RadioGroup rgGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_viewpager);
		mPager = (MyViewPager) findViewById(R.id.mypager);

		rgGroup = (RadioGroup) findViewById(R.id.rg_group);

		// 初始化item的布局
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);

			// 将ImageView添加给自定义ViewPager
			mPager.addView(view);
		}

		// 初始化测试页面
		View testPage = View.inflate(this, R.layout.test_page, null);
		mPager.addView(testPage, 2);// 在第三个位置添加测试页面

		// 初始化RadioButton
		for (int i = 0; i < mImageIds.length + 1; i++) {// 加上测试页面
			RadioButton rb = new RadioButton(this);
			rb.setId(i);// 以位置作为id设置
			rgGroup.addView(rb);
		}
		
		rgGroup.check(0);//第一个默认选中
		
		//设置RaidoButton选中事件
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int position = checkedId;//id就是当前的位置
				mPager.setCurrentItem(position);
			}
		});
		
		//当页面切换是,更新Raidobutton位置
		mPager.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSeleted(int position) {
				int id = position;
				rgGroup.check(id);
			}
		});
	}

}

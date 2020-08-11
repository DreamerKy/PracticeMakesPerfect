package com.example.dreams;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.dreams.widget.MyBigView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class BigViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_view);
//		MyBigView myBigView = findViewById(R.id.myBigView);
//		WeakReference w = new WeakReference(this);
//		SubsamplingScaleImageView myBigView = findViewById(R.id.myBigView);
//		InputStream open = null;
//		try {
////			open = getAssets().open("world.jpg");
//			open = getAssets().open("mulu.jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		myBigView.setImage(open);
//		myBigView.setImage(ImageSource.asset("world.jpg"));
	}
}

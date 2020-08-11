package com.example.dreams;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dreams.widget.MySwitch;

public class SwitchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch);

		MySwitch mySwitch = (MySwitch) findViewById(R.id.myswitch);
		//4. 在前端界面设置控件的监听
		mySwitch.setOnCheckChangeListener(new MySwitch.OnCheckChangeListener() {

			@Override
			public void onCheckChanged(View view, boolean isChecked) {
				Toast.makeText(getApplicationContext(), "当前状态:" + isChecked,
						Toast.LENGTH_SHORT).show();
			}
		});

		// CheckBox cb = new CheckBox(this);
		// cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		//
		// }
		// });
	}
}

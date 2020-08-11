package com.example.dreams;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.dreams.widget.PathMeasureHookView;

import androidx.appcompat.app.AppCompatActivity;

public class FlowLayoutActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("handleMessage");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        handler.sendEmptyMessage(1);
    }
}

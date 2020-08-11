package com.example.dreams;

import android.os.Bundle;

import com.example.dreams.widget.ClockView;

import androidx.appcompat.app.AppCompatActivity;

public class ClockViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_view);
        ClockView clock_view = findViewById(R.id.my_clock_view);
        clock_view.setCompleteDegree(100f);
    }
}

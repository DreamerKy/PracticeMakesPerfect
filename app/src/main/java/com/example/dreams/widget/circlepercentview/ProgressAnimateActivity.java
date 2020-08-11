package com.example.dreams.widget.circlepercentview;

import android.os.Bundle;
import android.view.View;

import com.example.dreams.R;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressAnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_animate);

        final CirclePercentView circlePercentView = findViewById(R.id.circlePercentView);
        circlePercentView.setOnCircleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float percent = (float) (Math.random() * 99 + 1);
                circlePercentView.setCurPercent(percent);
            }
        });

    }
}

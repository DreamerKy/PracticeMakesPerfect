package com.example.dreams;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.dreams.widget.ClockView;

import androidx.appcompat.app.AppCompatActivity;

public class CameraViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        ViewStub sampleStub = findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(R.layout.sample_camera_rotate_hitting_face);
        sampleStub.inflate();
        ViewStub practiceStub = findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(R.layout.practice_camera_rotate_hitting_face);
        practiceStub.inflate();
    }
}

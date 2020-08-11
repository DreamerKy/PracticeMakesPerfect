package com.example.dreams;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.dreams.widget.DegreeView;
import com.example.dreams.widget.PathMeasureHookView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DegreeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_view);
        final DegreeView degreeView = findViewById(R.id.my_degree_view);
        degreeView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(degreeView.isStart()){
                    degreeView.stop();
                }else{
                    degreeView.start();
                }
            }
        });
    }
}

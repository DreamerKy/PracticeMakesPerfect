package com.example.dreams.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dreams.R;

import androidx.appcompat.app.AppCompatActivity;

public class ChoosePictureActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        button = findViewById(R.id.btn_choose);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

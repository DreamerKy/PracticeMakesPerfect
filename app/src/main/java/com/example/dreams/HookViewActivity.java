package com.example.dreams;

import android.os.Bundle;

import com.example.dreams.widget.PathMeasureHookView;

import androidx.appcompat.app.AppCompatActivity;

public class HookViewActivity extends AppCompatActivity {

    private PathMeasureHookView hookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_view);
        hookView = findViewById(R.id.hook_view);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hookView != null) {
            hookView.stopAnimator();
        }
    }
}

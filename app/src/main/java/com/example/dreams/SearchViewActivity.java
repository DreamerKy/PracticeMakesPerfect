package com.example.dreams;

import android.os.Bundle;

import com.example.dreams.widget.PathMeasureHookView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchViewActivity extends AppCompatActivity {

    private PathMeasureHookView hookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

    }
}

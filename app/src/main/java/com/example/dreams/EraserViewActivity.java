package com.example.dreams;

import android.app.Activity;
import android.os.Bundle;

import com.example.dreams.widget.XferModeEraserView;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/11/6.
 */
public class EraserViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XferModeEraserView(this));
    }

}

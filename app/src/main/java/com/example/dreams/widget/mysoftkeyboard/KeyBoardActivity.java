package com.example.dreams.widget.mysoftkeyboard;

import android.os.Bundle;

import com.example.dreams.R;
import com.example.dreams.widget.mysoftkeyboard.view.MySoftKeyboardEditext;

import androidx.appcompat.app.AppCompatActivity;


public class KeyBoardActivity extends AppCompatActivity {

    private MySoftKeyboardEditext softKeyboardEditext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        initView();

        //获取密码
        String password = softKeyboardEditext.getPassword();
    }

    private void initView() {
        softKeyboardEditext=(MySoftKeyboardEditext)findViewById(R.id.password_edit);
    }

}

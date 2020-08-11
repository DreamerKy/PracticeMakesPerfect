package com.example.dreams;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookActivity extends BaseActivity {

    @Override
    public int layoutResId() {
        return R.layout.activity_hook;
    }

    @Override
    public void initViews() {
        Button button = findViewById(R.id.butHook);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(HookActivity.this, RedPaperActivity.class));
                Toast.makeText(HookActivity.this, "" + ((Button)v).getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        try {
            startHook(button);
        } catch (Exception e) {
            System.out.println("拦截是失败");
            e.printStackTrace();
        }
    }

    private void startHook(View view) throws Exception {

        Class viewClass = Class.forName("android.view.View");
        Method getListenerInfoMethod = viewClass.getDeclaredMethod("getListenerInfo");
        getListenerInfoMethod.setAccessible(true);
        Object mListenerInfo = getListenerInfoMethod.invoke(view);

        Class listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
        Field mOnLongClickListenerField = listenerInfoClass.getDeclaredField("mOnLongClickListener");
        mOnLongClickListenerField.setAccessible(true);
        final Object mOnLongClickListener = mOnLongClickListenerField.get(mListenerInfo);


        Object proxyInstance = Proxy.newProxyInstance(getClassLoader(), new Class[]{View.OnLongClickListener.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("拦截成功");
                Button button = new Button(HookActivity.this);
                button.setText("你竟然敢长安我！！！");
                return method.invoke(mOnLongClickListener,button);
            }
        });

        mOnLongClickListenerField.set(mListenerInfo,proxyInstance);
    }

}

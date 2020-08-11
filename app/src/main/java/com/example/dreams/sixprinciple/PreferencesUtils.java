package com.example.dreams.sixprinciple;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by likaiyu on 2020/4/7.
 */
public class PreferencesUtils {
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private Object object;
    private static PreferencesUtils instance;

    private PreferencesUtils() {

    }

    public static PreferencesUtils getInstance() {
        if (instance == null) {
            synchronized (PreferencesUtils.class) {
                if (instance == null) {
                    instance = new PreferencesUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public synchronized void save(String key, Object object) {
        if (editor == null) {
            editor = preferences.edit();
        }
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            // 保存integer 类型
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            // 保存 boolean 类型
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            // 保存float类型
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            // 保存long类型
            editor.putLong(key, (Long) object);
        } else {
            //保存对象
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(object.getClass().getName() + " must implements Serializable !");
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                String resultBase64 = Base64.encodeToString(
                        baos.toByteArray(), Base64.DEFAULT);
                editor.putString(key, resultBase64);
                Log.d(this.getClass().getSimpleName(), "save object success");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(this.getClass().getSimpleName(), "save object error");
            }
        }
        editor.apply();
    }

    public Object get(String key, Object defaultValue) {
        if (defaultValue == null) {
            getObject(key);
        }
        String type = defaultValue.getClass().getSimpleName();
        if ("String".equals(type)) {
            return preferences.getString(key, (String) defaultValue);
        } else if ("Integer".equals(type)) {
            return preferences.getInt(key, (Integer) defaultValue);
        } else if ("Boolean".equals(type)) {
            return preferences.getBoolean(key, (Boolean) defaultValue);
        } else if ("Float".equals(type)) {
            return preferences.getFloat(key, (Float) defaultValue);
        } else if ("Long".equals(type)) {
            return preferences.getLong(key, (Long) defaultValue);
        }
        return getObject(key);
    }

    public Object getObject(String key) {
        String source = preferences.getString(key, "");
        byte[] result = Base64.decode(source.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(result);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();
            Log.d(this.getClass().getSimpleName(), "Get object success");
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(this.getClass().getSimpleName(), "Get object is error");
        return null;
    }

    public synchronized void remove(String key) {
        if (editor == null) {
            editor = preferences.edit();
        }
        editor.remove(key);
        editor.apply();
    }


}

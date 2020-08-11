package com.example.dreams.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 表操作具体实现类
 * Created by likaiyu on 2020/7/19.
 */

public class BaseDao<T> implements IBaseDao<T> {
    //数据库操作引用
    private SQLiteDatabase sqLiteDatabase;
    //要操作的表所对应的类对象
    private Class<T> clazz;
    //表名
    private String tableName;
    //缓存容器，缓存字段名和对应的成员变量
    private Map<String, Field> cacheMap;

    protected void init(Class<T> clazz, SQLiteDatabase sqLiteDatabase) {
        this.clazz = clazz;
        this.sqLiteDatabase = sqLiteDatabase;
        cacheMap = new HashMap<>();
        initCacheMap();
        //确保这个类对象所对应的表已经存在了
        tableName = this.clazz.getAnnotation(DBTable.class).value();
        String tableCreateStr = getTableCreateStr();
        this.sqLiteDatabase.execSQL(tableCreateStr);
    }

    private void initCacheMap() {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            DBField annotation = declaredField.getAnnotation(DBField.class);
            if (annotation == null) {
                continue;
            }
            String fieldName = annotation.value();
            cacheMap.put(fieldName, declaredField);
        }
    }

    private String getTableCreateStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(tableName + "(");
        //获取成员变量上的注解
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            DBField annotation = declaredField.getAnnotation(DBField.class);
            if (annotation == null) {
                continue;
            }
            String fieldName = annotation.value();
            //获取成员变量类型
            Class<?> type = declaredField.getType();
            //判断类型，根据不同类型拼装不同语句
            if (type == String.class) {
                stringBuffer.append(fieldName + " TEXT,");
            } else if (type == Integer.class) {
                stringBuffer.append(fieldName + " INTEGER,");
            } else if (type == Long.class) {
                stringBuffer.append(fieldName + " LONG,");
            } else if (type == Double.class) {
                stringBuffer.append(fieldName + " DOUBLE,");
            } else if (type == byte[].class) {
                stringBuffer.append(fieldName + " BLOB,");
            } else {
                //不支持的类型
                continue;
            }
        }
        if (stringBuffer.charAt(stringBuffer.length() - 1) == ',') {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Override
    public long insert(Object object) {
        Map<String, String> map = getObjectValue(object);
        ContentValues contentValues = mapToContent(map);
//        //获取object中的值
//        Field[] declaredFields = object.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            try {
//                String value = (String) field.get(object);
//                DBField annotation = field.getAnnotation(DBField.class);
//                if (annotation == null) {
//                    continue;
//                }
//                String key = annotation.value();
//                contentValues.put(key, value);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        long insert = sqLiteDatabase.insert(tableName, null, contentValues);
        return insert;
    }

    /**
     * 抽取公共方法
     * 将Map中的数据放到ContentValue
     * @param map
     * @return
     */
    private ContentValues mapToContent(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String fieldName = iterator.next();
            String value = map.get(fieldName);
            contentValues.put(fieldName,value);
        }
        return contentValues;
    }

    /**
     * 公共方法
     * 从object中获取需要操作的字段数据
     * @param object
     * @return
     */
    public Map<String,String> getObjectValue(Object object){
        //将参数从object中拿出来
        Map<String,String> map = new HashMap<>();
        Iterator<String> iterator = cacheMap.keySet().iterator();
        while (iterator.hasNext()){
            String fieldName = iterator.next();
            Field field = cacheMap.get(fieldName);
            field.setAccessible(true);
            String value = null;
            try {
                value = (String) field.get(object);
                map.put(fieldName,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(value == null){
                continue;
            }
        }
        return map;
    }
}

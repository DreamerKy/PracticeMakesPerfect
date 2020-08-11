package com.kotlin.vip.skinlibrary.model;

import android.content.res.TypedArray;
import android.util.SparseIntArray;

/**
 * Created by likaiyu on 2019/11/28.
 * 临时Bean对象，用于存储控件的key,value
 * 动态加载的场景，键值对是否存储SharedPreferences
 */
public class AttrsBean {
    private SparseIntArray resourcesMap;
    private static final int DEFAULT_VALUE = -1;

    public AttrsBean() {
        resourcesMap = new SparseIntArray();
    }

    public void saveViewResource(TypedArray typedArray, int[] styleable) {
        for (int i = 0; i < typedArray.length(); i++) {
            int key = styleable[i];
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            resourcesMap.put(key, resourceId);
        }
    }

    public int getViewResource(int styleable) {
        return resourcesMap.get(styleable);

    }


}

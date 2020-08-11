package com.kotlin.vip.arouterapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.kotlin.vip.annotation.model.RouterBean;

import java.util.Map;

/**
 * Created by likaiyu on 2019/12/12.
 */
public final class RouterManager1 {

    private String group;
    private String path;
    private static volatile RouterManager1 instance;
    private LruCache<String, ARouterLoadGroup> groupCache;
    private LruCache<String, ARouterLoadPath> pathCache;
    private static final String GROUP_FILE_PREFIX_NAME = ".ARouter$$Group$$";

    public static RouterManager1 getInstance() {
        if (instance == null) {
            synchronized (RouterManager1.class) {
                if (instance == null) {
                    instance = new RouterManager1();
                }
            }
        }
        return instance;
    }

    private RouterManager1() {
        groupCache = new LruCache<>(163);
        pathCache = new LruCache<>(163);
    }

    public BundleManager1 build(String path) {

        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            throw new IllegalArgumentException("注解未按规范配置");
        }

        group = subFromPath2Group(path);

        this.path = path;
        return new BundleManager1();
    }

    private String subFromPath2Group(String path) {
        if (path.lastIndexOf("/") == 0) {
            throw new IllegalArgumentException("注解未按规范配置");
        }
        String finalGroup = path.substring(1, path.indexOf("/", 1));
        if (TextUtils.isEmpty(finalGroup)) {
            throw new IllegalArgumentException("注解未按规范配置");
        }
        return finalGroup;
    }

    /**
     * 开始跳转
     */

    Object navigation( Context context, BundleManager1 bundleManager1, int code) {
        String groupClassName = "com.kotlin.vip.apt" + GROUP_FILE_PREFIX_NAME + group;
        Log.e("netease >>> ", "groupClassName -> " + groupClassName);
        try {
            ARouterLoadGroup groupLoad = groupCache.get(group);
            if (groupLoad == null) {
                Class<?> clazz = Class.forName(groupClassName);
                groupLoad = (ARouterLoadGroup) clazz.newInstance();
                groupCache.put(group, groupLoad);
            }

            //获取路由路径类ARouter$$Path$$app
            if (groupLoad.loadGroup().isEmpty()) {
                throw new RuntimeException("路由加载失败");
            }
            ARouterLoadPath pathLoad = pathCache.get(path);
            if (pathLoad == null) {
                Class<?> clazz = groupLoad.loadGroup().get(group);
                pathLoad = (ARouterLoadPath) clazz.newInstance();
                pathCache.put(path, pathLoad);
            }

            if (pathLoad != null) {
                Map<String, RouterBean> pathRouterBeanMap = pathLoad.loadPath();
                if (pathRouterBeanMap.isEmpty()) {
                    throw new RuntimeException("路由加载失败");
                }
                RouterBean routerBean = pathRouterBeanMap.get(path);
                if (routerBean != null) {
                    switch (routerBean.getType()) {
                        case ACTIVITY:
                            Intent intent = new Intent(context, routerBean.getClazz());
                            intent.putExtras(bundleManager1.getBundle());
                            if (bundleManager1.isResult()) {
                                ((Activity) context).setResult(code, intent);
                                ((Activity) context).finish();
                            }
                            if (code > 0) {
                                ((Activity) context).startActivityForResult(intent, code, bundleManager1.getBundle());
                            } else {
                                context.startActivity(intent, bundleManager1.getBundle());
                            }
                            break;
                        case CALL:
                            Class<?> clazz = routerBean.getClazz();
                            Call call = (Call) clazz.newInstance();
                            bundleManager1.setCall(call);
                            return bundleManager1.getCall();
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}

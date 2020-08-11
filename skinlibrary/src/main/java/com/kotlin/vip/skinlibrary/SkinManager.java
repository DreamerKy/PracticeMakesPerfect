package com.kotlin.vip.skinlibrary;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.kotlin.vip.skinlibrary.model.SkinCache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2019/11/28.
 */
public class SkinManager {

    private static SkinManager instance;
    private Application application;
    private Resources skinResources;
    private Resources appResources;
    private boolean isDefaultSkin = true;
    private Map<String, SkinCache> cacheSkin;
    private String skinPackageName;
    private static final String ADD_ASSET_PATH = "addAssetPath";

    private SkinManager(Application application) {
        this.application = application;
        appResources = application.getResources();
        cacheSkin = new HashMap<>();
    }

    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinManager.class) {
                if (instance == null) {
                    instance = new SkinManager(application);
                }
            }
        }
    }

    public static SkinManager getInstance() {
        return instance;
    }

    public boolean isDefaultSkin() {
        return isDefaultSkin;
    }

    public void loaderSkinResources(String skinPath) {

        if (TextUtils.isEmpty(skinPath)) {
            isDefaultSkin = true;
            return;
        }
//        if (cacheSkin.containsKey(skinPath)) {
//            isDefaultSkin = false;
//            SkinCache skinCache = cacheSkin.get(skinPath);
//            if (null != skinCache) {
//                skinResources = skinCache.getSkinResources();
//                skinPackageName = skinCache.getSkinPackageName();
//                return;
//            }
//        }

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod(ADD_ASSET_PATH, String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager, skinPath);

            skinResources = new Resources(assetManager,
                    appResources.getDisplayMetrics(), appResources.getConfiguration());
            skinPackageName = application.getPackageManager()
                    .getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;

            isDefaultSkin = TextUtils.isEmpty(skinPackageName);
            if (!isDefaultSkin) {
                cacheSkin.put(skinPath, new SkinCache(skinResources, skinPackageName));
            }

        } catch (Exception e) {
            e.printStackTrace();
            isDefaultSkin = true;
        }
    }

    private int getSkinResourceIds(int resourceId) {
        if (isDefaultSkin) return resourceId;
        String resourceName = appResources.getResourceEntryName(resourceId);
        String resourceType = appResources.getResourceTypeName(resourceId);
        int skinResourceId = skinResources.getIdentifier(resourceName, resourceType, skinPackageName);
        isDefaultSkin = skinResourceId == 0;
        return skinResourceId == 0 ? resourceId : skinResourceId;
    }

    public int getColor(int themeColorId) {
        int ids = getSkinResourceIds(themeColorId);
        return isDefaultSkin ? appResources.getColor(ids) : skinResources.getColor(ids);
    }

    public ColorStateList getColorStateList(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getColorStateList(ids) : skinResources.getColorStateList(ids);
    }

    public Drawable getDrawableOrMinMap(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getDrawable(ids) : skinResources.getDrawable(ids);
    }

    public String getString(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getString(ids) : skinResources.getString(ids);
    }

    public Object getBackgroundOrSrc(int backgroundResourceId) {
        String resourceTypeName = appResources.getResourceTypeName(backgroundResourceId);
        switch (resourceTypeName) {
            case "color":
                return getColor(backgroundResourceId);
            case "mipmap":
            case "drawable":
                return getDrawableOrMinMap(backgroundResourceId);
        }
        return null;
    }

    public Typeface getTypeface(int textTypefaceResourceId) {
        String skinTypefacePath = getString(textTypefaceResourceId);
        if (TextUtils.isEmpty(skinTypefacePath)) {
            return Typeface.DEFAULT;
        }
        return isDefaultSkin ? Typeface.createFromAsset(appResources.getAssets(), skinTypefacePath) : Typeface.createFromAsset(skinResources.getAssets(), skinTypefacePath);
    }


}

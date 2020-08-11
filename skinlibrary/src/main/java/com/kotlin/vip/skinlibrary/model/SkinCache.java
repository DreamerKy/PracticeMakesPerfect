package com.kotlin.vip.skinlibrary.model;

import android.content.res.Resources;

/**
 * Created by likaiyu on 2019/12/2.
 */
public class SkinCache {

    private Resources skinResources;
    private String skinPackageName;
    public SkinCache(Resources skinResources,String skinPackageName){
        this.skinResources = skinResources;
        this.skinPackageName = skinPackageName;
    }

    public Resources getSkinResources() {
        return skinResources;
    }

    public String getSkinPackageName() {
        return skinPackageName;
    }

}

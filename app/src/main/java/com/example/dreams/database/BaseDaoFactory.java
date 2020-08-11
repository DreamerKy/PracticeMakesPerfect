package com.example.dreams.database;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;

/**
 * 框架层入口
 * Created by likaiyu on 2020/7/19.
 */

public class BaseDaoFactory {
    private static BaseDaoFactory baseDaoFactory = new BaseDaoFactory();
    private String path;
    private SQLiteDatabase sqLiteDatabase;

    private BaseDaoFactory() {

    }

    public void init(String path) {
        this.path = path;
        File file = new File(this.path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(this.path, null);
    }

    public static BaseDaoFactory getInstance() {
        return baseDaoFactory;
    }

    public <T> IBaseDao getBaseDao(Class<T> clazz) {
        BaseDao<T> baseDao = new BaseDao<>();
        baseDao.init(clazz, sqLiteDatabase);
        return baseDao;
    }


}

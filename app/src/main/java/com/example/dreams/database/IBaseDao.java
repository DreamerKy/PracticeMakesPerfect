package com.example.dreams.database;

/**
 * 所有表的操作类的基类
 * 增删改查
 * Created by likaiyu on 2020/7/19.
 */

public interface IBaseDao<T> {

    long insert(T t);

}

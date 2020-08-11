package com.example.dreams.database;

/**
 * Created by likaiyu on 2020/7/19.
 */

public class test {

    public static void main(String[] args) {
//        User user = new User("zhangsan","123456",11);
//        BaseDaoFactory.getInstance().init("data/data/com.example.dreams/database/test.db");
//        IBaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
//        baseDao.insert(user);

        String[] idcs = {"bjidc", "shidc"};
        int count = 0;
        for (int i = 0; i < 300; i++) {
            int randomIndex = Math.random() >= 0.99 ? 1 : 0;
            String result = randomIndex == 1 ? ++count + idcs[randomIndex] : idcs[randomIndex];
            System.out.println(result);
        }
    }

}

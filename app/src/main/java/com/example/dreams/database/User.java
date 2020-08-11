package com.example.dreams.database;

/**
 * Created by likaiyu on 2020/7/19.
 */
@DBTable("tb_user")
public class User {

    @DBField("_account")
    String account;

    @DBField("_password")
    String password;

    Integer age;

    public User(String account, String password, Integer age) {
        this.account = account;
        this.password = password;
        this.age = age;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

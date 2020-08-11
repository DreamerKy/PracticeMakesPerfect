package com.example.dreams.bean;

/**
 * Created by likaiyu on 2020/4/2.
 */
public class Test {

    public static String dealWithString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        str.trim();
        String[] arr = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("") && i != arr.length - 1) {
                stringBuilder.append(arr[i]).append(" ");
            } else {
                stringBuilder.append(arr[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "a  d dee     eee    ";
        String s1 = dealWithString(s);
        System.out.println(s1);
    }

}

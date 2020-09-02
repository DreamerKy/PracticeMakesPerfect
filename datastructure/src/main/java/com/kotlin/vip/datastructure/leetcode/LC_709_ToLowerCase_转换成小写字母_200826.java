package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/26.
 *
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 *
 * 输入: "Hello"
 * 输出: "hello"
 *
 * 输入: "LOVELY"
 * 输出: "lovely"
 *
 */
public class LC_709_ToLowerCase_转换成小写字母_200826 {

    public static void main(String[] args) {
        String res = toLowerCase("HKAHKHhKhakhakhsk");
        System.out.println(res);
    }

    public static String toLowerCase(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                stringBuilder.append((char) (c + 32));
            } else {
                stringBuilder.append(c);
            }
        }
        /*for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
//                char c = Character.toLowerCase(str.charAt(i));
                char c = (char) (str.charAt(i) + 32);
                stringBuilder.append(c);
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }*/
        return stringBuilder.toString();
    }
}

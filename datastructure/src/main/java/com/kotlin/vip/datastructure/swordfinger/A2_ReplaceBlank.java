package com.kotlin.vip.datastructure.swordfinger;

/**
 * Created by likaiyu on 2020/1/7.
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class A2_ReplaceBlank {

    public static String replaceBlank(String input) {
        if (input == null)
            return null;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(input.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

}

package com.kotlin.vip.datastructure.swordfinger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/3/10.
 * 求字符串集合中最长公共前缀
 */
public class A7_FindTheLongestCommonStr {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("ab");
        list.add("abe");
//        list.add("ad");
//        list.add("daaa");

        String theLongestCommonStr = findTheLongestCommonStr(list);
        System.out.println(theLongestCommonStr);
    }

    private static String findTheLongestCommonStr(List<String> list) {
        String maxPublicPrefix = "";
        int minLength = list.get(0).length();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() < minLength) {
                minLength = list.get(i).length();
            }
        }

        f1:
        for (int i = 0; i < minLength; i++) {
            int supposePrefixLength = i;
            String supposePrefix = list.get(0).substring(0, supposePrefixLength + 1);
            for (int j = 0; j < list.size(); j++) {
                if (!supposePrefix.equals(list.get(j).substring(0, supposePrefixLength + 1))) {
                    break f1;
                }
            }
            maxPublicPrefix = supposePrefix;
        }

        return maxPublicPrefix;
    }


}

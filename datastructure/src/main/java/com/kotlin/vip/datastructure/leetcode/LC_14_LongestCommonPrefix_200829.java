package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/29.
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 *
 */
public class LC_14_LongestCommonPrefix_200829 {

    public static void main(String[] args) {
        String[] nums = {"dog","racecar","car"};
        String res = longestCommonPrefix(nums);
        System.out.println(res);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String shortest = strs[0];
        for (String str : strs) {
            if (str.length() < shortest.length()) {
                shortest = str;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < shortest.length(); i++) {
            char current = shortest.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != current) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(current);
        }
        return stringBuilder.toString();
    }
}

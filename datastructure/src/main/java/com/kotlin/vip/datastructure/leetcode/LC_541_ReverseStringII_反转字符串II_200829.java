package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/29.
 *
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 *      如果剩余字符少于 k 个，则将剩余字符全部反转。
 *      如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *
 */
public class LC_541_ReverseStringII_反转字符串II_200829 {

    public static void main(String[] args) {
        String res = reverseStr2("abcdefg", 8);
        System.out.println(res);
    }

    public static String reverseStr2(String s,int k) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();
        if (k <= 1) return s;
        char[] a = s.toCharArray();
        for (int start = 0; start < len; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, len - 1);
            while (i < j) {
                char temp = a[i];
                a[i++] = a[j];
                a[j--] = temp;
            }
        }
        return new String(a);
    }


    public static String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();
        if (k <= 1) return s;
        if (k > len) return reverseString(s);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < len) {
            String temp;
            if ((index + 2 * k) < len) {
                temp = s.substring(index, index + 2 * k);
            } else {
                temp = s.substring(index);
            }
            index += 2 * k;
            String subStr = reserveStr(temp, k);
            stringBuilder.append(subStr);
        }
        return stringBuilder.toString();
    }

    public static String reserveStr(String str, int k) {
        if (str.length() < k) {
            return reverseString(str);
        } else {
            return reverseString(str.substring(0, k)) + str.substring(k);
        }
    }

    public static String reverseString(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] != arr[j]) {
                swap(arr, i, j);
            }
            i++;
            j--;
        }
        return new String(arr);
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

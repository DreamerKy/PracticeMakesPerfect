package com.kotlin.vip.datastructure.leetcode;

import com.kotlin.vip.datastructure.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaiyu on 2020/8/20.
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 */
public class LC_242_ValidAnagram_200820 {

    public static void main(String[] args) {
        boolean res = isAnagram3("anagram", "nagaram");
        System.out.println(res);
    }

    public static boolean isAnagram3(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
            table[t.charAt(i) - 'a']--;
        }
        for (int count : table) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            if (map.containsKey(sChars[i])) {
                map.put(s.charAt(i), map.get(sChars[i]) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (int i = 0; i < tChars.length; i++) {
            if (map.containsKey(tChars[i])) {
                map.put(t.charAt(i), map.get(tChars[i]) - 1);
            }
        }
        for (Integer val : map.values()) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 暴力
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        String s1 = Arrays.toString(chars);
        String s2 = Arrays.toString(chars1);
        return s1.equals(s2);
    }
}

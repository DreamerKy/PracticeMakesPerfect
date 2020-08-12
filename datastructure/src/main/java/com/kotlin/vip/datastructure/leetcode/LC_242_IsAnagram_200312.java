package com.kotlin.vip.datastructure.leetcode;

import java.util.Arrays;

/**
 * Created by likaiyu on 2020/3/12.
 * 有效的字母异位词
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class LC_242_IsAnagram_200312 {

    public boolean isAbagram(String s,String t){
        if(s.length()!=t.length()){
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1,str2);

    }

    public boolean isAbagram2(String s,String t){
        if(s.length()!=t.length()){
            return false;
        }
        int[] counter = new int[26];
        for(int i = 0;i<s.length();i++){
            counter[s.charAt(i)-'a']++;
            counter[t.charAt(i)-'a']--;
        }
        for(int count:counter){
            if(count!=0){
                return false;
            }
        }
        return true;
    }










}

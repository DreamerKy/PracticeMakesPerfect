package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/26.
 *
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 */
public class LC_58_LengthOfLastWord_最后一个单词的长度_200826 {

    public static void main(String[] args) {
        int res = lengthOfLastWord("a  ");
        System.out.println(res);
    }

    public static int lengthOfLastWord(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (res == 0) continue;
                break;
            }
            res++;
        }
        return res;
    }
}

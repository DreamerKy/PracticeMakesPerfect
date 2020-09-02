package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/30.
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 *
 */
public class LC_151_ReverseWordsInAString_翻转字符串里的单词_200830 {

    public static void main(String[] args) {
        String res = reverseWords2("  a good   example   ");
//        String res = trimSpace("  a good   example     ");
        System.out.println(res);
    }

    /**
     * 去掉字符串开头以及中间多余的空格
      * @param s
     * @return
     */
    public static StringBuilder trimSpace(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') ++left;
        while (left <= right && s.charAt(right) == ' ') --right;
        StringBuilder stringBuilder = new StringBuilder();
        while (left <= right) {
            char cur = s.charAt(left);
            if (cur != ' ' || stringBuilder.charAt(stringBuilder.length() - 1) != ' ')
                stringBuilder.append(cur);
            left++;
        }
        return stringBuilder;
    }

    //elpmaxe doog a
    public static String reverseWords2(String s) {
        if (s == null || s.length() == 0) return "";
        //去除了两端以及中建多余的空格
        StringBuilder trimStr = trimSpace(s);
        reverse(trimStr, 0, trimStr.length() - 1);
        reverseEachWord(trimStr);
        return trimStr.toString();
//        String[] s1 = reverseStr.split(" ");
//        for(int i = 0;i<s1.length;i++){
//            s1[i] = reverse(s1[i]);
//        }
//        return String.join(" ",s1);
    }

    private static void reverseEachWord(StringBuilder sb) {
        int len = sb.length();
        int start = 0, end = 0;
        while (start < len) {
            //循环至单词尾
            while(end<len && sb.charAt(end) != ' '){
                end ++;
            }
            //反转单词
            reverse(sb,start,end-1);
            start = end +1;
            end ++;
        }

    }

    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }


    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        String temp = reverse(s);
        temp = temp.trim();
        String[] strs = temp.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("")) {
                continue;
            }
            String reverse = reverse(strs[i]);
            if (i == strs.length - 1) {
                stringBuilder.append(reverse);
            } else {
                stringBuilder.append(reverse).append(' ');
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 去掉字符串开头以及中间多余的空格
     * @param s
     * @return
     */
    public static String trimSpaceToString(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') ++left;
        while (left <= right && s.charAt(right) == ' ') --right;
        StringBuilder stringBuilder = new StringBuilder();
        while (left <= right) {
            char cur = s.charAt(left);
            if (cur != ' ' || stringBuilder.charAt(stringBuilder.length() - 1) != ' ')
                stringBuilder.append(cur);
            left++;
        }
        return stringBuilder.toString();
    }

    public static String reverse(String str) {
        char[] arr = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (arr[i] != arr[j]) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            i++;
            j--;
        }
        return new String(arr);
    }

}

package com.kotlin.vip.datastructure.leetcode;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 *
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 *
 */
public class LC_771_JewelsAndStones_宝石与石头_200827 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        int i = numJewelsInStones("z", "ZZZ");
        byte[] bytes = "中".getBytes("utf-16");
        for(Byte b : bytes){
            System.out.print(Integer.toHexString(Byte.toUnsignedInt(b)));
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        // 1111111111111111111111111111111
        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
        // 7fffffff
        System.out.println(Character.toChars(65));
        System.out.println("\"中\" 默认 ：" + "中".getBytes().length);
        System.out.println("\"𣡕\" 默认 ：" + "𣡕".getBytes().length);
        System.out.println("\"中\" utf-8 ：" +"中".getBytes("utf-8").length);
        System.out.println("\"𣡕\" utf-8 ：" +"𣡕".getBytes("utf-8").length);
        System.out.println("\"中\" GBK ：" +"中".getBytes("GBK").length);
        System.out.println("\"𣡕\" GBK ：" +"𣡕".getBytes("GBK").length);
        System.out.println("\"中\" utf-16 ：" +"中".getBytes("utf-16").length);
        System.out.println("\"𣡕\" utf-16 ：" +"𣡕".getBytes("utf-16").length);
    }

    public static int numJewelsInStones2(String J, String S) {
        int res = 0;
        if (J == null || J.length() == 0 || S == null || S.length() == 0) {
            return res;
        }
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                res++;
            }
        }
        return res;
    }

    public static int numJewelsInStones(String J, String S) {
        int res = 0;
        if (J == null || J.length() == 0 || S == null || S.length() == 0) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] sChars = S.toCharArray();
        char[] JChars = J.toCharArray();
        for (char c : sChars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (char c : JChars) {
            res += map.getOrDefault(c, 0);
        }
        return res;
    }
}

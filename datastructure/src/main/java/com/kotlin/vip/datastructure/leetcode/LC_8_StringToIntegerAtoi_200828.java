package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/28.
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
 * 则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 提示：
 *
 *     本题中的空白字符只包括空格字符 ' ' 。
 *     假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。、
 *     如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 输入: "42"
 * 输出: 42
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 */
public class LC_8_StringToIntegerAtoi_200828 {

    public static void main(String[] args) {
        int res = mAtoi2("-2147483649");
        System.out.println(res);
    }

    public static int mAtoi2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = str.length();
        char[] charArray = str.toCharArray();
        //1、去除前导空格
        int index = 0;
        while (index < length && charArray[index] == ' ') {
            index++;
        }

        //2、如果已经遍历完则返回0
        if (index == length) {
            return 0;
        }

        //3、记录第一个加减字符
        int sign = 1;
        if (charArray[index] == '+') {
            index++;
        } else if (charArray[index] == '-') {
            sign = -1;
            index++;
        }

        //4、将后续出现的数字字符进行转换
        int res = 0;
        while (index < length) {
            char current = charArray[index];
            if (current < '0' || current > '9') {
                //非数字
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (current - '0') > 7)) {
                return Integer.MAX_VALUE;
            } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (current - '0') > 8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (current - '0');
            index++;
        }
        return res;
    }

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        String trimStr = str.trim();
        if (trimStr.length() == 0 || trimStr.equals("-") || trimStr.equals("+")) return 0;
        char operation = ' ';
        if (trimStr.startsWith("-") || trimStr.startsWith("+")) {
            operation = trimStr.charAt(0);
            trimStr = trimStr.substring(1);
        }
        if ((trimStr.charAt(0) < '0' || trimStr.charAt(0) > '9')) return 0;
        int res = 0;
        for (int i = 0; i < trimStr.length(); i++) {
            char current = trimStr.charAt(i);
            if (trimStr.charAt(i) < '0' || trimStr.charAt(i) > '9') {
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (current - '0') > 7)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (current - '0') > 8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + (operation == '-' ? -1 : 1) * (current - '0');
        }

        /*try {
            res = operation == '-' ? -Integer.parseInt(trimStr) : Integer.parseInt(trimStr);
        } catch (NumberFormatException e) {
            res = operation == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }*/
        return res;
    }
}

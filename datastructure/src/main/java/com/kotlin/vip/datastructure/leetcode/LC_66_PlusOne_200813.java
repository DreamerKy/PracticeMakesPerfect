package com.kotlin.vip.datastructure.leetcode;

import com.kotlin.vip.datastructure.Array;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位，数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 */
public class LC_66_PlusOne_200813 {

    public static void main(String[] args) {
        int[] nums = {9,8,9};
        int[] ints = plusOne2(nums);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
//            digits[i]++;
//            digits[i] = digits[i] % 10;
//            if (digits[i] != 0) {
//                return digits;
//            }
            if(digits[i]!=9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }


    /**
     * 未通过
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int digit : digits) {
            stringBuilder.append(digit);
        }
        String s = stringBuilder.toString();
        BigInteger bigInteger = new BigInteger(s);
        BigInteger bigIntegerOne = new BigInteger("1");
        BigInteger bigIntegerResult = bigInteger.add(bigIntegerOne);
        String resultString = bigIntegerResult.toString();
        int[] resultArr = new int[resultString.length()];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] =Integer.parseInt(String.valueOf(resultString.charAt(i))) ;
        }
        return resultArr;
    }
}

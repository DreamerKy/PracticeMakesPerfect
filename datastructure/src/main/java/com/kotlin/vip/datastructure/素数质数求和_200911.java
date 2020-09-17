package com.kotlin.vip.datastructure;

/**
 * Created by likaiyu on 2020/8/10.
 * <p>
 * 0到1000以内的素数
 */
public class 素数质数求和_200911 {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println("0到1000以内的素数：");
        int sum = 0, n = 0;
        for (int i = 2; i <= 10; i++) {
            boolean isSuShu = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isSuShu = false;
                    break;
                }
            }
            if (isSuShu) {
                System.out.print(i);
                System.out.print(" ");
                sum += i;
                n++;
                if (n % 15 == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("素数之和：" + sum);
        System.out.println("耗时：" + (System.currentTimeMillis() - l) / 1000);
    }
}

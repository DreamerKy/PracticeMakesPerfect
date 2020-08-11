package com.example.dreams.adapter.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by likaiyu on 2019/11/13.
 */
public class test {
    static final int MAXIMUM_CAPACITY = 1 << 30;
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        int rounded = number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (rounded = Integer.highestOneBit(number)) != 0
                ? (Integer.bitCount(number) > 1) ? rounded << 1 : rounded
                : 1;

        return rounded;
    }

    public static void main(String[] args) throws IOException {

//        FileInputStream fileInputStream = new FileInputStream(new File("aaa"));
//        BufferedReaderAdapter adapter = new BufferedReaderAdapter(new InputStreamReaderImpl(fileInputStream));
//        BufferedReader bufferedReader = adapter.getBufferedReader();
        int i = roundUpToPowerOf2(9);
        int j = Integer.bitCount(8);
        System.out.println(i);
        System.out.println(j);

    }
}

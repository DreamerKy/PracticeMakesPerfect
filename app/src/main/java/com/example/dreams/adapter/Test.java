package com.example.dreams.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("main");
        FileReader fileReader = new FileReader("d:/aaa.txt");
        FileInputStream fileInputStream = new FileInputStream(new File("d:/aaa.txt"));
        ReaderAdapter adapter = new ReaderAdapter(new ISReaderImpl(fileInputStream));
        BufferedReader bufferedReader = adapter.getBufferedReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine())!=null){
            sb.append(line);
            sb.append("\n");
        }
        bufferedReader.close();
        System.out.println(sb.toString());
    }
}

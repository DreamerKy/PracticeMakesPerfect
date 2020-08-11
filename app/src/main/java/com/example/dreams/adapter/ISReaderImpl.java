package com.example.dreams.adapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class ISReaderImpl implements ISReader {

    private InputStream inputStream;

    public ISReaderImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStreamReader getISReader() {
        try {
            return new InputStreamReader(inputStream,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

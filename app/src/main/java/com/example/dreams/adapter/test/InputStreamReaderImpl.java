package com.example.dreams.adapter.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by likaiyu on 2019/11/13.
 */
public class InputStreamReaderImpl implements IInputStreamReader {

    private InputStream inputStream;

    public InputStreamReaderImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStreamReader getInputStreamReader() {
        try {
            return new InputStreamReader(inputStream,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

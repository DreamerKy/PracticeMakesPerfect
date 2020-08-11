package com.example.dreams.adapter.test;

import java.io.BufferedReader;

/**
 * Created by likaiyu on 2019/11/13.
 */
public class BufferedReaderAdapter implements IBufferedReader {

    private IInputStreamReader iInputStreamReader;

    BufferedReaderAdapter(IInputStreamReader iInputStreamReader) {
        this.iInputStreamReader = iInputStreamReader;
    }

    @Override
    public BufferedReader getBufferedReader() {
        return new BufferedReader(this.iInputStreamReader.getInputStreamReader());
    }

}

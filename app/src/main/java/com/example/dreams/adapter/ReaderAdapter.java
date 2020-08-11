package com.example.dreams.adapter;

import java.io.BufferedReader;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class ReaderAdapter implements IReader {
    private ISReader isReader;

    public ReaderAdapter(ISReader isReader) {
        this.isReader = isReader;
    }

    @Override
    public BufferedReader getBufferedReader() {
        return new BufferedReader(isReader.getISReader());
    }
}

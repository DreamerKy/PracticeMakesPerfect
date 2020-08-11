package com.example.dreams.adapter.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by likaiyu on 2019/11/13.
 */
public interface IBufferedReader {
    BufferedReader getBufferedReader();
}

interface IBReader{
    BufferedReader getBufferedReader();
}

interface IISReader{
    InputStreamReader getInputStreamReader();
}

class IISReaderImpl implements IISReader{

    private InputStream inputStream;

    public IISReaderImpl(InputStream inputStream) {

        this.inputStream = inputStream;
    }

    @Override
    public InputStreamReader getInputStreamReader() {
        try {
            return new InputStreamReader(inputStream,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class BufferedReadeAdapter implements IBReader{

    private IISReaderImpl iisReader;

    public BufferedReadeAdapter(IISReaderImpl iisReader) {
        this.iisReader = iisReader;
    }

    @Override
    public BufferedReader getBufferedReader() {
        return new BufferedReader(iisReader.getInputStreamReader());
    }

    public static void main(String[] args) {

        try {
            IISReaderImpl iisReader = new IISReaderImpl(new FileInputStream(new File("d:/aaa.txt")));
            BufferedReadeAdapter adapter = new BufferedReadeAdapter(iisReader);
            BufferedReader bufferedReader = adapter.getBufferedReader();
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                sb.append("呵呵呵");
                sb.append("\n");
            }

            bufferedReader.close();
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}




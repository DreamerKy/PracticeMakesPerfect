package com.example.dreams.okhttp.interceptor;

import com.example.dreams.okhttp.Interceptor;
import com.example.dreams.okhttp.Request;
import com.example.dreams.okhttp.Response;
import com.example.dreams.okhttp.SocketRequestServer;
import com.example.dreams.okhttp.chain.Chain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by likaiyu on 2020/1/23.
 */
public class ConnectionServiceInterceptor implements Interceptor {

    private final String TAG = "conn";

    @Override
    public Response doNext(Chain chain) throws IOException {

        System.out.println("ConnectionServiceInterceptor真正工作");

        SocketRequestServer srs = new SocketRequestServer();
        Request request = chain.getRequest();
        Socket socket = new Socket(srs.getHost(request), srs.getPort(request));
        //请求
        OutputStream os = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        String requestHeaderAll = srs.getRequestHeaderAll(request);
        bufferedWriter.write(requestHeaderAll);
        bufferedWriter.flush();//刷新缓冲区，真正写出去
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Response response = new Response();
        String readLine = bufferedReader.readLine();
        String[] strings = readLine.split(" ");
        //响应码
        response.setStatusCode(Integer.parseInt(strings[1]));
        //响应体
        String readerLine;
        try {
            while ((readerLine = bufferedReader.readLine()) != null) {
                if ("".equals(readerLine)) {
                    //空行下就是响应体
                    response.setBody(bufferedReader.readLine());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ConnectionServiceInterceptor 拿到Response返回");

        return response;
    }
}

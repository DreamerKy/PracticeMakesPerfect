package com.example.dreams.httpformupload;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by likaiyu on 2020/4/21.
 */
public class RealCall implements Call{

    private final Request originalRequest;
    private final OkHttpClient client;

    public RealCall(Request request, OkHttpClient okHttpClient){
        this.originalRequest = request;
        this.client = okHttpClient;
    }

    public static Call newCall(Request request,OkHttpClient okHttpClient){
        return new RealCall(request, okHttpClient);
    }

    @Override
    public void enqueue(CallBack callback) {
        //异步请求
        AsyncCall asyncCall = new AsyncCall(callback);
        client.dispatcher.enqueue(asyncCall);
    }

    @Override
    public Response execute() {
        //同步请求
        return null;
    }

    final class AsyncCall extends NamedRunnable{
        private final CallBack callBack;

        public AsyncCall(CallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        protected void execute() {
            //真正的开始访问网络
            Request request = originalRequest;
            try {
                URL url = new URL(request.url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection instanceof HttpsURLConnection){
                    //https的一些操作
                    ((HttpsURLConnection) urlConnection).setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return false;
                        }
                    });
                    ((HttpsURLConnection) urlConnection).setSSLSocketFactory(new SSLSocketFactory() {
                        @Override
                        public String[] getDefaultCipherSuites() {
                            return new String[0];
                        }

                        @Override
                        public String[] getSupportedCipherSuites() {
                            return new String[0];
                        }

                        @Override
                        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
                            return null;
                        }

                        @Override
                        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
                            return null;
                        }

                        @Override
                        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
                            return null;
                        }

                        @Override
                        public Socket createSocket(InetAddress host, int port) throws IOException {
                            return null;
                        }

                        @Override
                        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
                            return null;
                        }
                    });
                }
                urlConnection.setReadTimeout(5000);
                //开始写数据
                urlConnection.setRequestMethod(request.method.name);
                urlConnection.setDoOutput(request.method.doOutPut());
                RequestBody requestBody = request.requestBody;
                if(requestBody != null){
                    //添加头信息
                    urlConnection.setRequestProperty("Content-Type",requestBody.getContentType());
                    urlConnection.setRequestProperty("Content-Length",Long.toString(requestBody.getContentLength()));
                }
                urlConnection.connect();
                //写内容
                if(requestBody != null){
                    requestBody.onWriteBody(urlConnection.getOutputStream());
                }
                //获取返回数据
                int responseCode = urlConnection.getResponseCode();
                if(responseCode == 200){
                    InputStream inputStream = urlConnection.getInputStream();
                    Response response = new Response(inputStream);
                    callBack.onResponse(RealCall.this,response);
                }
            }catch (IOException e){
                callBack.onFailure(RealCall.this,e);
            }
        }
    }

}

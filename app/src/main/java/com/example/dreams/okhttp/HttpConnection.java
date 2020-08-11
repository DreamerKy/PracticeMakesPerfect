package com.example.dreams.okhttp;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by likaiyu on 2020/1/11.
 */
public class HttpConnection {
    private long lastUseTime;
    private Socket socket;


    public HttpConnection(final String host,final int port){
        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnectionAction(String host,int port){
        if(socket == null){
            return false;
        }
        try {
            if(socket.getPort() == port && socket.getInetAddress().getHostName().equals(host) ){
                return true;
            }
        }catch (Exception e){

        }

        return false;
    }

    public long getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(long lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public void closeSocket(){
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

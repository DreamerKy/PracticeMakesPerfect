package com.example.dreams.okhttp;

/**
 * Created by likaiyu on 2020/1/11.
 */
public class ConnectionManager {
    private ConnectionPool connectionPool;

    public ConnectionManager() {
        connectionPool = new ConnectionPool();
    }

    public void useConnection(String host, int port) {
        HttpConnection connection = connectionPool.getConnection(host, port);
        if (connection == null) {
            connection = new HttpConnection(host, port);
            connection.setLastUseTime(System.currentTimeMillis());
            connectionPool.putConnection(connection);
        } else {
            //ReuseConnection
        }
    }
}

class Test{
    public static void main(String[] args) {
//        ConnectionManager connectionManager = new ConnectionManager();
//        connectionManager.useConnection("aaa",8080);

        System.out.println(Integer.bitCount(5));

    }
}

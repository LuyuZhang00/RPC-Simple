package com.barry.rpc.transport;

/**
 * 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 */
public interface TransportServer {

    void init(int port, RequestHandler handler);

    void start();

    void stop();
}

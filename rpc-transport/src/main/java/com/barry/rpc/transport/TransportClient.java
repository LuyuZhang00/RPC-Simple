package com.barry.rpc.transport;

import com.barry.rpc.Peer;

import java.io.InputStream;

/**
 * 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 * @author 21083
 */

public interface TransportClient {
    void connect(Peer peer);

    /**
     * @param request
     * @return
     */
    InputStream write(InputStream data);

    void close();
}

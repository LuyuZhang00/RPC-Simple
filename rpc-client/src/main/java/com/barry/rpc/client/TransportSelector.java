package com.barry.rpc.client;

import com.barry.rpc.Peer;
import com.barry.rpc.transport.TransportClient;

import java.util.List;

/**
 * 选择哪个server去连接
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:07
 */
public interface TransportSelector {

    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    void release(TransportClient client);

    void close();


}

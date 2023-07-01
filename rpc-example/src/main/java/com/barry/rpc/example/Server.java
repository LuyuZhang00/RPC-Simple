package com.barry.rpc.example;

import com.barry.rpc.server.RpcServer;
import com.barry.rpc.server.RpcServerConfig;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:48
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}

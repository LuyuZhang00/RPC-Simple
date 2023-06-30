package com.barry.rpc.example;

import com.barry.rpc.server.RpcServer;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:48
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}

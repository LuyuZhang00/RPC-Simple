package com.barry.rpc.example;

import com.barry.rpc.client.RpcClient;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:48
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);
        System.out.println(r1);
        System.out.println(r2);
    }
}

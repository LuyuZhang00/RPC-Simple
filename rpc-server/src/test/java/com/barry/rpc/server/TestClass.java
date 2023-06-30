package com.barry.rpc.server;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 17:45
 */
public class TestClass implements TestInterface {

    @Override
    public void hello() {
        System.out.println("hello");
    }
}

package com.barry.rpc.example;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:49
 */
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}

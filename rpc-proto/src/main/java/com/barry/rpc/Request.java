package com.barry.rpc;

import lombok.Data;

/**
 * 表示RPC的一个请求
 */
@Data
public class Request {

    private ServiceDescriptor service;

    private Object[] parameters;

}

package com.barry.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 服务实例
 * @Author : Luyu Zhang
 * @create 2023-06-30 17:19
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;
}

package com.barry.rpc.server;

import com.barry.rpc.Request;
import com.barry.rpc.common.utils.ReflectionUtils;

/**
 * 调用具体服务
 * @Author : Luyu Zhang
 * @create 2023-06-30 18:53
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}

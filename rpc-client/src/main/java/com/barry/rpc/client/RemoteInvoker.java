package com.barry.rpc.client;

import com.barry.rpc.Request;
import com.barry.rpc.Response;
import com.barry.rpc.ServiceDescriptor;
import com.barry.rpc.codec.Decoder;
import com.barry.rpc.codec.Encoder;
import com.barry.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:34
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    private Class clazz;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
//        Class clazz;
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response resp = null;
        try {
            client = selector.select();
            
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            
            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            resp = decoder.decode(inBytes, Response.class);
            
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error: " + e.getClass() + " : " + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}

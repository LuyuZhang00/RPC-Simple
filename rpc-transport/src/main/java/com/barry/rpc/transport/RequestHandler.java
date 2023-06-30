package com.barry.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 */
public interface RequestHandler {

/**
     * 处理请求
     * @param request
     * @return
     */
    void onRequest(InputStream receive, OutputStream response);


}

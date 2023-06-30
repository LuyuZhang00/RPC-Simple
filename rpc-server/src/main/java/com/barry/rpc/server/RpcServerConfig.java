package com.barry.rpc.server;

import com.barry.rpc.codec.Decoder;
import com.barry.rpc.codec.Encoder;
import com.barry.rpc.codec.JSONDecoder;
import com.barry.rpc.codec.JSONEncoder;
import com.barry.rpc.transport.HTTPTransportServer;
import com.barry.rpc.transport.TransportServer;
import lombok.Data;

/**
 * 服务端配置
 * @Author : Luyu Zhang
 * @create 2023-06-30 17:13
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;

}

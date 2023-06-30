package com.barry.rpc.client;

import com.barry.rpc.Peer;
import com.barry.rpc.codec.Decoder;
import com.barry.rpc.codec.Encoder;
import com.barry.rpc.codec.JSONDecoder;
import com.barry.rpc.codec.JSONEncoder;
import com.barry.rpc.transport.HttpTransportClient;
import com.barry.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Luyu Zhang
 * @create 2023-06-30 19:20
 */
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;

    private List<Peer> servers= Arrays.asList(
            new Peer("127.0.0.1", 3000));
}

package com.barry.rpc.codec;

import com.alibaba.fastjson2.JSON;

/**
 * 序列化
 */
public class JSONEncoder implements Encoder {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}

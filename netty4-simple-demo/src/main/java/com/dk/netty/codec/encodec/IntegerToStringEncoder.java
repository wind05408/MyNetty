package com.dk.netty.codec.encodec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-10 18:33
 **/
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {//实现继承自 MessageToMessageEncoder
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));//转 Integer 为 String，并添加到 MessageBuf
    }
}

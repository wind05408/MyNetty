package com.dk.netty.codec.encodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-10 18:31
 **/
public class ShortToByteEncoder extends MessageToByteEncoder<Short> { //实现继承自 MessageToByteEncoder

    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg); //写 Short 到 ByteBuf
    }
}

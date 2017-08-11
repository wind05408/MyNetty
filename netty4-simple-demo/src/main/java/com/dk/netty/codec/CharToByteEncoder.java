package com.dk.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-11 11:29
 **/
public class CharToByteEncoder extends MessageToByteEncoder<Character> { //继承 MessageToByteEncoder
    @Override
    protected void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        out.writeChar(msg);//写 char 到 ByteBuf
    }
}

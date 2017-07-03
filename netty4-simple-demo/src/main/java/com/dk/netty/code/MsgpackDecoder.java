package com.dk.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * MsgpackDecoder
 *
 * @author dk
 * @date 2017/7/3 15:41
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println("开始进行解码...。。。。。。。。。。。。");
        final byte[] array;
        final int length = msg.readableBytes();

        array = new byte[length];

        msg.getBytes(msg.readerIndex(),array,0,length);
        MessagePack msgpack = new MessagePack();

        Object result = msgpack.read(array);
        out.add(result);
        System.out.println("进行解码结束。。。。。。。。。。。。");

    }
}

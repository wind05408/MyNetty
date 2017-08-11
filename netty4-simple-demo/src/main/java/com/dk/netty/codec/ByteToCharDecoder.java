package com.dk.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-11 11:29
 **/
public class ByteToCharDecoder extends ByteToMessageDecoder {//继承 ByteToMessageDecoder
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>=2){
            out.add(in.readChar());//写 char 到 MessageBuf
        }
    }
}

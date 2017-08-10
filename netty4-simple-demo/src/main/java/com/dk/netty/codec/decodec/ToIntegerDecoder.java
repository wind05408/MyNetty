package com.dk.netty.codec.decodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 每次从入站的 ByteBuf 读取四个字节，解码成整形，并添加到一个 List （本例是指 Integer）,
 * 当不能再添加数据到 list 时，它所包含的内容就会被发送到下个 ChannelInboundHandler
 * @create 2017-08-10 16:16
 **/
public class ToIntegerDecoder extends ByteToMessageDecoder { //实现继承了 ByteToMessageDecode 用于将字节解码为消息
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>=4){ //检查可读的字节是否至少有4个 ( int 是4个字节长度)
            out.add(in.readInt()); //从入站 ByteBuf 读取 int ， 添加到解码消息的 List 中
        }
    }
}

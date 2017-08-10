package com.dk.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: ReplayingDecoder 是 byte-to-message 解码的一种特殊的抽象基类，
 * 读取缓冲区的数据之前需要检查缓冲区是否有足够的字节，使用ReplayingDecoder就无需自己检查；
 * 若ByteBuf中有足够的字节，则会正常读取；若没有足够的字节则会停止解码
 *  ReplayingDecoder 继承自 ByteToMessageDecoder
 *
 *  ReplayingDecoder局限性:
 *  1.不是所有的标准 ByteBuf 操作都被支持，如果调用一个不支持的操作会抛出UnreplayableOperationException
 *  2.ReplayingDecoder 略慢于 ByteToMessageDecoder
 *
 * @create 2017-08-10 16:18
 **/
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception { //实现继承自 ReplayingDecoder 用于将字节解码为消息
           out.add(in.readInt());//从入站 ByteBuf 读取整型，并添加到解码消息的 List 中
    }
}

//    更多 Decoder:
//    io.netty.handler.codec.LineBasedFrameDecoder 通过结束控制符("\n" 或 "\r\n").解析入站数据。
//    io.netty.handler.codec.http.HttpObjectDecoder 用于 HTTP 数据解码

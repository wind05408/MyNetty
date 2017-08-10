package com.dk.netty.codec.decodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 在解码时处理太大的帧  TooLongFrameException
 * @create 2017-08-10 16:28
 **/
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {

    private static final int MAX_FRAME_SIZE = 1024;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {//实现继承 ByteToMessageDecoder 来将字节解码为消息
        int readable = in.readableBytes();
        if (readable > MAX_FRAME_SIZE) { //2检测缓冲区数据是否大于 MAX_FRAME_SIZE
            in.skipBytes(readable);        //3忽略所有可读的字节，并抛出 TooLongFrameException 来通知 ChannelPipeline 中的 ChannelHandler 这个帧数据超长
            throw new TooLongFrameException("Frame too big!");
        }
        // do something
    }
}

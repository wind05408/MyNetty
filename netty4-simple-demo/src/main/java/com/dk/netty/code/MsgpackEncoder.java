package com.dk.netty.code;

import com.google.common.base.Throwables;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;


/**
 * Created with IntelliJ IDEA
 * MsgpackEncoder
 *
 * @author dk
 * @date 2017/7/3 15:45
 */
public class MsgpackEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        System.out.println("开始进行解码...。。。。。。。。。。。。");
        //负责将POJO对象编码为byte数组
        MessagePack msgpack = new MessagePack();
        byte[] raw = null;
        try {
            raw = msgpack.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Throwables.propagateIfPossible(e);
        }
        out.writeBytes(raw);
        System.out.println("进行解码结束。。。。。。。。。。。。");
    }
}

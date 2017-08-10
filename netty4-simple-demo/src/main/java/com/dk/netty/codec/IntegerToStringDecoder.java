package com.dk.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 也就是说,入站消息是按照在类定义中声明的参数类型(这里是 Integer) 而不是 ByteBuf来解析的。
 * 在之前的例子,解码消息(这里是String)将被添加到List，并传递到下个 ChannelInboundHandler
 * @create 2017-08-10 16:25
 **/
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception { //实现继承自 MessageToMessageDecoder
      out.add(String.valueOf(msg));//通过 String.valueOf() 转换 Integer 消息字符串
    }
}

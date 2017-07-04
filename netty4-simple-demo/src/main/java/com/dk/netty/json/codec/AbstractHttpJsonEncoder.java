package com.dk.netty.json.codec;

import com.dk.common.json.FasterJsonTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * AbstractHttpJsonEncoder
 *
 * @author dk
 * @date 2017/7/4 18:10
 */
public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {

    final static Charset UTF_8 = Charset.forName("utf-8");

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) throws Exception {
        String jsonstr = FasterJsonTool.writeValueAsString(body);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(jsonstr,UTF_8);
        return encodeBuf;
    }
}

package com.dk.netty.json.codec;

import com.dk.common.json.FasterJsonTool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * AbstractHttpJsonDecoder
 *
 * @author dk
 * @date 2017/7/4 18:14
 */
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {

    private Class<?> clazz;
    private boolean isPrint;

    private final static Charset UTF_8 = Charset.forName("UTF-8");

    public AbstractHttpJsonDecoder(Class<?> clazz) {
        this(clazz,false);
    }

    public AbstractHttpJsonDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
        String content = body.toString(UTF_8);
        if(isPrint)
            System.out.println("The body is : " + content);

        Object result = FasterJsonTool.readValue(content,clazz);

        return result;
    }
}

package com.dk.netty.xml;

import com.dk.netty.common.JAXBUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.xml.bind.JAXBException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * AbstractHttpXmlDecoder
 *
 * @author dk
 * @date 2017/7/4 15:41
 */
public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {

    private Class<?> clazz;
    private boolean isPrint;
    private final static String CHARSET_NAME = "UTF-8";
    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    public AbstractHttpXmlDecoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    public AbstractHttpXmlDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext context, ByteBuf body) throws JAXBException {
        String content = body.toString(UTF_8);
        if(isPrint){
            System.out.println("The body is : " + content);
        }
        Object result = JAXBUtils.unmarshal(content, clazz);
        return result;

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

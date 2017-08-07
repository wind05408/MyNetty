package com.dk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-07 16:47
 **/
public class RWMain {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.readByte());

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        System.out.println(readerIndex+"  "+writerIndex);
        buf.writeByte((byte)'?');
        System.out.println(buf.readerIndex()+"  "+buf.writerIndex());
        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();
        System.out.println(buf.refCnt());
        buf.release();
        System.out.println(buf.refCnt());


    }
}

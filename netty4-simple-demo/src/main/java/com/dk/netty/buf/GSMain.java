package com.dk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-07 16:44
 **/
public class GSMain {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);    //1
        System.out.println((char)buf.getByte(0));                    //2

        int readerIndex = buf.readerIndex();                        //3
        int writerIndex = buf.writerIndex();

        buf.setByte(0, (byte)'B');                            //4

        System.out.println((char)buf.getByte(0));                    //5
        assert readerIndex == buf.readerIndex();                    //6
        assert writerIndex ==  buf.writerIndex();
    }
}

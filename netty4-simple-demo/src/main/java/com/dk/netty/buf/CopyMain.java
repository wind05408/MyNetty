package com.dk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  copy 数据不是共享的
 * @create 2017-08-07 16:35
 **/
public class CopyMain {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);     //1

        ByteBuf copy = buf.copy(0, 14);               //2
        System.out.println(copy.toString(utf8));      //3

        buf.setByte(0, (byte) 'J');                   //4
        assert buf.getByte(0) != copy.getByte(0);
    }
}

package com.dk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: slice 数据是共享的
 * @create 2017-08-07 16:27
 **/
public class SliceMain {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8); //1

        ByteBuf sliced = buf.slice(0, 14);          //2
        System.out.println(sliced.toString(utf8));  //3

        buf.setByte(0, (byte) 'J');                 //4
        assert buf.getByte(0) == sliced.getByte(0);
        System.out.println( (char)buf.getByte(0) +"  =? "+ (char)sliced.getByte(0));
    }
}

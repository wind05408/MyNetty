package com.dk.netty.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-11 11:28
 **/
//CombinedByteCharCodec 的参数是解码器和编码器的实现用于处理进站字节和出站消息
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder,CharToByteEncoder> {

    public CombinedByteCharCodec() {
        //传递 ByteToCharDecoder 和 CharToByteEncoder 实例到 super 构造函数来委托调用使他们结合起来
        super(new ByteToCharDecoder(),new CharToByteEncoder());
    }

}

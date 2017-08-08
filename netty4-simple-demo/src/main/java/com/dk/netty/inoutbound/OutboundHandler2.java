package com.dk.netty.inoutbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-08 18:23
 **/
public class OutboundHandler2  extends ChannelOutboundHandlerAdapter {
    private static Logger logger  = LoggerFactory.getLogger(OutboundHandler2.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("OutboundHandler2.write");
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}

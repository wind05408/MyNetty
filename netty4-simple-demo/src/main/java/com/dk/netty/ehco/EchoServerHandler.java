package com.dk.netty.ehco;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * EchoServerHandler
 *
 * @author dk
 * @date 2017/6/22 16:19
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("server channel read...");
        // 收到消息直接打印输出
        logger.info(ctx.channel().remoteAddress() + " Say : " + msg);

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, "UTF-8");
            logger.info("server channel read msg:{}", body);
        }catch (Exception e){
            e.printStackTrace();
        }

        String response = "hello from server";
        ByteBuf resp = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("server channel read complete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        logger.error("server caught exception", cause);
        ctx.close();
    }
}

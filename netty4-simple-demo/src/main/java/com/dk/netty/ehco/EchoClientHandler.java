package com.dk.netty.ehco;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * EchoClientHandler
 *
 * @author dk
 * @date 2017/6/22 16:33
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ByteBuf firstMessage;

    public EchoClientHandler(){
        byte[] req = "Hello from client11111111111111111".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    //服务器的连接被建立后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client channel active");
        // Send the message to Server
        logger.info("client send req...");
        ctx.writeAndFlush(firstMessage);
    }

    //数据后从服务器接收到调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, "UTF-8");
            logger.info("client channel read msg:{}", body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    捕获异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        logger.error("client caught exception", cause);
        ctx.close();
    }
}
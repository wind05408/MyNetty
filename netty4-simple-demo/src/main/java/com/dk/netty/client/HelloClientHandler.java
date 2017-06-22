package com.dk.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with IntelliJ IDEA
 * HelloClientHandler
 *
 * @author dk
 * @date 2017/6/22 12:11
 */
public class HelloClientHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server say : " + msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
}

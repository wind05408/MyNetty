package com.dk.netty.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-11 14:58
 **/
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean client;
    private final boolean startTls;

    //使用构造函数来传递 SSLContext 用于使用(startTls 是否启用)
    public SslChannelInitializer(SslContext context, boolean client, boolean startTls) {
        this.context = context;
        this.client = client;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine engine = context.newEngine(ch.alloc());
        engine.setUseClientMode(client);
        ch.pipeline().addFirst("ssl", new SslHandler(engine, startTls));  //4
    }
}

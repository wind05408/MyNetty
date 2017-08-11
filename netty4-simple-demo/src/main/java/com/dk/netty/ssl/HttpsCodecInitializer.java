package com.dk.netty.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 启用 HTTPS，只需添加 SslHandler
 * @create 2017-08-11 16:09
 **/
public class HttpsCodecInitializer   extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean client;

    public HttpsCodecInitializer(SslContext context, boolean client) {
        this.context = context;
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine engine = context.newEngine(ch.alloc());
        //添加 SslHandler 到 pipeline 来启用 HTTPS
        pipeline.addFirst("ssl", new SslHandler(engine));  //1

        if (client) {
            //client: 添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());  //2
        } else {
            //server: 添加 HttpServerCodec ，如果是 server 模式的
            pipeline.addLast("codec", new HttpServerCodec());  //3
        }
    }
}

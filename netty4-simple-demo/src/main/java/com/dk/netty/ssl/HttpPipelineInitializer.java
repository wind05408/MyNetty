package com.dk.netty.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-11 15:58
 **/
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if(client){
            //client: 添加 HttpResponseDecoder 用于处理来自 server 响应
            pipeline.addLast("decoder",new HttpResponseDecoder());
            //client: 添加 HttpRequestEncoder 用于发送请求到 server
            pipeline.addLast("encoder",new HttpRequestEncoder());
        }else {
            //server: 添加 HttpRequestDecoder 用于接收来自 client 的请求
            pipeline.addLast("decoder",new HttpRequestDecoder());
            //server: 添加 HttpResponseEncoder 用来发送响应给 client
            pipeline.addLast("encoder",new HttpResponseEncoder());
        }
    }
}

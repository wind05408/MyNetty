package com.dk.netty.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: HTTP消息聚合
 * @create 2017-08-11 16:02
 *
 * 装 ChannelPipeline 中的初始化之后,你能够对不同 HttpObject 消息进行操作。
 * 但由于 HTTP 请求和响应可以由许多部分组合而成，你需要聚合他们形成完整的消息。为了消除这种繁琐任务，
 * Netty 提供了一个聚合器,合并消息部件到 FullHttpRequest 和 FullHttpResponse 消息。这样您总是能够看到完整的消息内容
 **/
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpAggregatorInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if(client){
            //client: 添加 HttpClientCodec
            pipeline.addLast("codec",new HttpClientCodec());
        }else {
            //server: 添加 HttpServerCodec 作为我们是 server 模式时
            pipeline.addLast("codec",new HttpServerCodec());
        }
        //添加 HttpObjectAggregator 到 ChannelPipeline, 使用最大消息值是 512kb
        pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
    }
}

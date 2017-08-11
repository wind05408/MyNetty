package com.dk.netty.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: HTTP 压缩
 * @create 2017-08-11 16:07
 *
 * 使用 HTTP 时建议压缩数据以减少传输流量，压缩数据会增加 CPU 负载，现在的硬件设施都很强大，大多数时候压缩数据时一个好主意。
 * Netty 支持“gzip”和“deflate”，为此提供了两个 ChannelHandler 实现分别用于压缩和解压。
 **/
public class HttpAggregatorInitializer2 extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpAggregatorInitializer2(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if(client){
            //client: 添加 HttpClientCodec
            pipeline.addLast("codec",new HttpClientCodec());
            //client: 添加 HttpContentDecompressor 用于处理来自服务器的压缩的内容
            pipeline.addLast("decompressor",new HttpContentDecompressor()); //2
        }else {
            //server: 添加 HttpServerCodec 作为我们是 server 模式时
            pipeline.addLast("codec",new HttpServerCodec());
            //server: HttpContentCompressor 用于压缩来自 client 支持的 HttpContentCompressor
            pipeline.addLast("compressor",new HttpContentCompressor()); //4
        }
    }
}

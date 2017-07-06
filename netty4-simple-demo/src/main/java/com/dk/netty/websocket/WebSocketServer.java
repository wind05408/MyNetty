package com.dk.netty.websocket;

import com.dk.netty.json.codec.HttpJsonRequestDecoder;
import com.dk.netty.json.codec.HttpJsonResponseEncoder;
import com.dk.netty.json.server.HttpJsonServerHandler;
import com.dk.netty.xml.Order;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA
 * WebSocketServer
 *
 * @author dk
 * @date 2017/7/6 16:00
 */
public class WebSocketServer {

    public void bind(int port) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-codec",new HttpServerCodec());
                            pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                            pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                            pipeline.addLast("handler",new WebSocketServerHandler());


                        }
                    });
            ChannelFuture future = b.bind(new InetSocketAddress(port)).sync();
            System.out.println("Web socket server started at port " + port
                    + '.');
            System.out
                    .println("Open your browser and navigate to http://localhost:"
                            + port + '/');
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new WebSocketServer().bind(port);
    }
}

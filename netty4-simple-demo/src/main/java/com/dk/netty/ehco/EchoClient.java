package com.dk.netty.ehco;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * EchoClient
 *
 * @author dk
 * @date 2017/6/22 16:29
 */
public class EchoClient {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            EchoClientHandler echoClientHandler = new EchoClientHandler();
                            p.addLast(echoClientHandler);
                        }
                    });

            ChannelFuture f = b.connect(host,port).sync();
            logger.info("client connect to host:{}, port:{}", host, port);

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        new EchoClient(Constants.HOST, 8082).send();
    }



}

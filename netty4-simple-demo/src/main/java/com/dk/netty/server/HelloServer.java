package com.dk.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * Created with IntelliJ IDEA
 * HelloServer
 *
 * @author dk
 * @date 2017/6/22 11:44
 */
public class HelloServer {

    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber = 7878;

    public static void main(String[] args) throws InterruptedException {
        //boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup);
//            b.channel(NioServerSocketChannel.class);
//            b.childHandler(new HelloServerInitializer());

            //简写
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInitializer());//用于添加相关的Handler




//            // 服务器绑定端口监听
//            ChannelFuture f = b.bind(portNumber).sync();
//            // 监听服务器关闭监听
//            f.channel().closeFuture().sync();

            //服务器绑定端口监听  监听服务器关闭监听
            b.bind(portNumber).sync().channel().closeFuture().sync();

            // 可以简写为
            /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

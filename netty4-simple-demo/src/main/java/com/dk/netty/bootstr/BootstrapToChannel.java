package com.dk.netty.bootstr;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-16 17:24
 **/
public class BootstrapToChannel {
    public void bind(int port){
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //创建一个新的 ServerBootstrap 来创建新的 SocketChannel 管道并且绑定他们
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //指定 EventLoopGroups 从 ServerChannel 和接收到的管道来注册并获取 EventLoops
        serverBootstrap.group(boosGroup,workGroup)
                .channel(NioServerSocketChannel.class)//指定 Channel 类来使用
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {//设置处理器用于处理接收到的管道的 I/O 和数据
                    ChannelFuture connectFuture;

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        //创建一个新的 Bootstrap 来连接到远程主机
                        Bootstrap bootstrap = new Bootstrap();
                        //设置管道类
                        bootstrap.channel(NioSocketChannel.class)
                                .handler(new SimpleChannelInboundHandler<ByteBuf>() {//设置处理器来处理 I/O
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                        System.out.println("Reveived data");
                                    }
                                });
                        //使用相同的 EventLoop 作为分配到接收的管道
                        bootstrap.group(ctx.channel().eventLoop());
                        //连接到远端
                        connectFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1",8080));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        if(connectFuture.isDone()){
                            //连接完成处理业务逻辑 (比如, proxy)
                        }
                    }
                });

        //通过配置了的 Bootstrap 来绑定到管道
        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(port));//
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("server bound");
                }else {
                    System.out.println("Bound attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        BootstrapToChannel bootstrapToChannel = new BootstrapToChannel();
        bootstrapToChannel.bind(8080);
    }
}

package com.dk.netty.ehco;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * EchoServer
 *
 * @author dk
 * @date 2017/6/22 16:11
 */
//1.设置端口值（抛出一个 NumberFormatException 如果该端口参数的格式不正确）+
//2.呼叫服务器的 start() 方法
//3.创建 EventLoopGroup
//4.创建 ServerBootstrap
//5.指定使用 NIO 的传输 Channel
//6.设置 socket 地址使用所选的端口
//7.添加 EchoServerHandler 到 Channel 的 ChannelPipeline
//8.绑定的服务器;sync 等待服务器关闭
//9.关闭 channel 和 块，直到它被关闭
//10.关机的 EventLoopGroup，释放所有资源。
public class EchoServer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public  void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();

            logger.info("server bind port:{}", port);
            System.out.println(EchoServer.class.getName() +
                    "started and listen on" + f.channel().localAddress());


            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(Constants.PORT).run();
    }

}

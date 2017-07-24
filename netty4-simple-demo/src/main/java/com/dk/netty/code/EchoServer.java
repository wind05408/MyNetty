package com.dk.netty.code;

import com.dk.netty.ehco.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created with IntelliJ IDEA
 * EchoServer
 *
 * @author dk
 * @date 2017/7/3 15:48
 */
public class EchoServer {
    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程池 Configure the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();

            //NioEventLoopGroup 实际就是 Reactor 线程池，负责调度和执行客户端的接入、
            // 网络读写事件的处理、用户自定义任务和定时任务的执行。
            // 通过 ServerBootstrap 的 group 方法将两个 EventLoopGroup 实例传入
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
//                            1.	系统编解码框架-ByteToMessageCodec；
//                            2.	通用基于长度的半包解码器-LengthFieldBasedFrameDecoder;
//                            3.	码流日志打印Handler-LoggingHandler；
//                            4.	SSL安全认证Handler-SslHandler；
//                            5.	链路空闲检测Handler-IdleStateHandler；
//                            6.	流量整形Handler-ChannelTrafficShapingHandler;
//                            7.	Base64编解码-Base64Decoder和Base64Encoder。

                            //读数据的时候用decoder解码
                            ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                            //写数据的时候用encoder编码
                            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                            //
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new EchoServer().bind(port);
    }
}
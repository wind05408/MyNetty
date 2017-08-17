package com.dk.netty.bootstr;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-17 16:41
 **/
public class NettyAttrMain {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        private static final int IDEL_TIME_OUT = 10;
                        private static final int READ_IDEL_TIME_OUT = 4;
                        private static final int WRITE_IDEL_TIME_OUT = 5;

                        private AttributeKey<Long> START_TIME = AttributeKey.newInstance("START_TIME");

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //idle事件监听
                            ch.pipeline().addLast("idleStateHandler", new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, IDEL_TIME_OUT));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelRegistered(ChannelHandlerContext ctx)//链接事件监听
                                        throws Exception {
                                    System.out.println("channel register");
                                    //设置链接属性，记录链接开始的时间
                                    ctx.channel().attr(START_TIME).setIfAbsent(System.currentTimeMillis());
//                                    ctx.attr(START_TIME).set(System.currentTimeMillis());
                                }

                                @Override
                                public void channelUnregistered(//断开链接事件
                                                                ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("channel unregister");
                                }

                                @Override
                                public void userEventTriggered(//事件监听
                                                               ChannelHandlerContext ctx, Object evt)
                                        throws Exception {
                                    //监听idle事件
                                    if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
                                        IdleStateEvent event = (IdleStateEvent) evt;
                                        //获取链接相关的属性（即链接开始的时间戳）
                                        Long startTime = ctx.channel().attr(START_TIME).get();
                                        if (startTime == null)
                                            //not arrive forever
                                            return;
                                        //read idle
                                        if (event.state() == IdleState.READER_IDLE)
                                            System.out.println("read idle" + startTime);
                                            //write idle
                                        else if (event.state() == IdleState.WRITER_IDLE)
                                            System.out.println("write idle" + startTime);
                                            //read and write idle
                                        else if (event.state() == IdleState.ALL_IDLE)
                                            System.out.println("all idle" + startTime);
                                    }
                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}

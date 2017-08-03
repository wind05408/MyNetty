package com.dk.netty.ehco;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * EchoServerHandler
 *
 * @author dk
 * @date 2017/6/22 16:19
 */
//1.@Sharable 标识这类的实例之间可以在 channel 里面共享
//2.日志消息输出到控制台
//3.将所接收的消息返回给发送者。注意，这还没有冲刷数据
//4.冲刷所有待审消息到远程节点。关闭通道后，操作完成
//5.打印异常堆栈跟踪
//6.关闭通道
@ChannelHandler.Sharable  //1 标识这类的实例之间可以在 channel 里面共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    1.	链路注册；
//    2.	链路激活；
//    3.	链路断开；
//    4.	接收到请求消息；
//    5.	请求消息接收并处理完毕；
//    6.	发送应答消息；
//    7.	链路发生异常；
//    8.	发生用户自定义事件。

    //每个信息入站都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("server channel read...");
        // 收到消息直接打印输出
        logger.info(ctx.channel().remoteAddress() + " Say : " + msg);

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, "UTF-8");
            logger.info("server channel read msg:{}", body);
        }catch (Exception e){
            e.printStackTrace();
        }

        String response = "hello from server";
        ByteBuf resp = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(resp);
    }

    // 通知处理器最后的 channelread() 是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("server channel read complete");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        logger.error("server caught exception", cause);
        cause.printStackTrace();                //5
        ctx.close();//6
    }

    public EchoServerHandler() {
        super();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    protected void ensureNotSharable() {
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable() {
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}

// 这种使用 ChannelHandler 的方式体现了关注点分离的设计原则，并简化业务逻辑的迭代开发的要求。
// 处理程序很简单;它的每一个方法可以覆盖到“hook（钩子）”在活动周期适当的点。
// 很显然，我们覆盖 channelRead因为我们需要处理所有接收到的数据

// 覆盖 exceptionCaught 使我们能够应对任何 Throwable 的子类型。
// 在这种情况下我们记录，并关闭所有可能处于未知状态的连接。
// 它通常是难以 从连接错误中恢复，所以干脆关闭远程连接。
// 当然，也有可能的情况是可以从错误中恢复的，所以可以用一个更复杂的措施来尝试识别和处理 这样的情况。

package com.dk.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * WebSocketServerHandler
 *
 * @author dk
 * @date 2017/7/6 17:32
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传统的HTTP接入
        if(msg instanceof FullHttpRequest){
            handlerHttpRequest(ctx,(FullHttpRequest)msg);
            //webSocket接入
        }else  if(msg instanceof WebSocketFrame){
            handlerWebSocketFrame(ctx,(WebSocketFrame)msg);
        }

    }

    private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
        // 如果HTTP解码失败，返回HHTP异常
        if(!msg.decoderResult().isSuccess()||(!"websocket".equals(msg.headers().get("Upgrade")))){
            sendHttpResponse(ctx,msg,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(msg);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(),msg);
        }

    }


    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        // 判断是否是关闭链路的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        //判断是否是Ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 本例程仅支持文本消息，不支持二进制消息
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }

        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        logger.info(String.format("%s received %s", ctx.channel(), request));
        ctx.channel().write(new TextWebSocketFrame(request
                                         + " , 欢迎使用Netty WebSocket服务，现在时刻："
                                         + new java.util.Date().toString()));

    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest msg, FullHttpResponse response) {
        // 返回应答给客户端
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(),
                    CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(response, response.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if (!HttpUtil.isKeepAlive(msg) || response.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);

        }
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

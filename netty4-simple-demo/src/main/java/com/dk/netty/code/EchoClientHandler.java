package com.dk.netty.code;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created with IntelliJ IDEA
 * EchoClientHandler
 *
 * @author dk
 * @date 2017/7/3 15:50
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    private UserInfo[] userInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfos[i] = userInfo;
            userInfo.setUserID(i);
            userInfo.setUserName("ABDCEFG-->" + i);
        }
        return userInfos;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* UserInfo userInfo = new UserInfo();
        userInfo.setUserID(0);
        userInfo.setUserName("ABDCEFG-->" + 0);*/
        UserInfo[] userInfos = userInfo();
        for (int i = 0; i < userInfos.length; i++) {
            ctx.writeAndFlush(userInfos[i]);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到信息:" + msg);
//        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//        ctx.close();
    }
}

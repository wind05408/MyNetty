package com.dk.netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.netty.handler.ipfilter.RuleBasedIpFilter;

/**
 * Created with IntelliJ IDEA
 * HelloClientInitializer
 *
 * @author dk
 * @date 2017/6/22 12:09
 */
public class HelloClientInitializer extends ChannelInitializer {

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         * */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 客户端的逻辑
        pipeline.addLast("handler", new HelloClientHandler());


//        //ip check
//        String[] ip = {};
//        int count = ip.length;
//        IpSubnetFilterRule[] ipsf = new IpSubnetFilterRule[count];
//        for( int i=0;i<count;i++){
//            ipsf[i] = new IpSubnetFilterRule(ip[i],16, IpFilterRuleType.REJECT);
//        }
//        pipeline.addLast(new RuleBasedIpFilter(ipsf));
    }
}

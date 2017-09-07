package com.dk.juc.mod.disruptor.demo1;

import com.lmax.disruptor.WorkHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:41
 **/
public class Consumer  implements WorkHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("消费者C1消费了一条消息");
    }
}

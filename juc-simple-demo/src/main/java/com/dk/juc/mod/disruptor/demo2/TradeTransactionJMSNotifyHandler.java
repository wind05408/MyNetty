package com.dk.juc.mod.disruptor.demo2;

import com.lmax.disruptor.EventHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:55
 **/
public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //do send jms message
        System.out.println("最后一个消费者C3");
    }
}

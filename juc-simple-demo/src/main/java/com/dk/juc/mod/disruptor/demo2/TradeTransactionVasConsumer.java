package com.dk.juc.mod.disruptor.demo2;

import com.lmax.disruptor.EventHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:57
 **/
public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        System.out.println("第一个消费者C1");
    }
}

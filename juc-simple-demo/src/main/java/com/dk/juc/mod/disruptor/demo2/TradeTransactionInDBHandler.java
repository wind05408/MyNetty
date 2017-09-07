package com.dk.juc.mod.disruptor.demo2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:51
 **/
public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        //这里做具体的消费逻辑
//        event.setId(UUID.randomUUID().toString());//简单生成下ID
//        System.out.println("TradeTransactionInDBHandler:"+event.getId());
        System.out.println("第二个消费者C2消费了消息");
    }
}
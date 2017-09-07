package com.dk.juc.mod.disruptor.demo2;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:56
 **/
public class  TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
    private Random random=new Random();
    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }
    private TradeTransaction generateTradeTransaction(TradeTransaction trade){
        trade.setPrice(random.nextDouble()*9999);
        return trade;
    }
}

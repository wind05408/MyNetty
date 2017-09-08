package com.dk.juc.mod.disruptor.demo4;

import com.lmax.disruptor.WorkHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-08 18:46
 **/
public class Consumer implements WorkHandler<DisData> {
    @Override
    public void onEvent(DisData event) throws Exception {
        System.out.println(Thread.currentThread().getId()+":Event:--"+event.getValue()*event.getValue()+"--");
    }
}

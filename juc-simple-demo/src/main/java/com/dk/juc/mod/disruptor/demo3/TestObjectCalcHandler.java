package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:44
 **/
public class TestObjectCalcHandler implements EventHandler<TestObject>,WorkHandler<TestObject> {

    @Override
    public void onEvent(TestObject event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(TestObject event) throws Exception {
        System.out.println("thread: " + Thread.currentThread().getId() + " object: " + event.getValue() + "计算");
    }

}

package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:43
 **/
public class TestObjectNotifyHandler implements EventHandler<TestObject>,WorkHandler<TestObject> {


    @Override
    public void onEvent(TestObject event) throws Exception {
        //you can send jms message
        System.out.println("thread: " + Thread.currentThread().getId() + " object: " + event.getValue() + "发消息");
    }

    @Override
    public void onEvent(TestObject event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }
}

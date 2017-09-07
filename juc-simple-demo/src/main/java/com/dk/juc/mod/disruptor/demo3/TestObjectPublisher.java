package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:43
 **/
public class TestObjectPublisher implements Runnable {

    Disruptor<TestObject> disruptor;
    private CountDownLatch latch;
    private static int LOOP = 10;// 模拟10次

    public TestObjectPublisher(CountDownLatch latch, Disruptor<TestObject> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TestObjectTranslator testObjectTranslator = new TestObjectTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(testObjectTranslator);
        }
        latch.countDown();
    }
}

package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:36
 **/
public class DisruptorMain1 {
    public static void main(String[] args) {
        int size = 1<<10;
        ExecutorService executors = Executors.newCachedThreadPool();
        Disruptor<TestObject> disruptor = new Disruptor<TestObject>(new TestObjectFactory(), size, executors);
        disruptor.handleEventsWith(new TestObjectAnalysisHandler());
        RingBuffer<TestObject> ringBuffer = disruptor.start();
        for (long i = 0; i < 1000; i++) {
            long seq = ringBuffer.next();
            try {
                TestObject valueEvent = ringBuffer.get(seq);
                valueEvent.setValue(i);
            } finally {
                //发布通知，并且这一步一定要放在finally中，因为调用了ringBuffer.next(),就一定要发布，否则会导致错乱
                ringBuffer.publish(seq);
            }
        }
        disruptor.shutdown();
        executors.shutdown();
    }
}

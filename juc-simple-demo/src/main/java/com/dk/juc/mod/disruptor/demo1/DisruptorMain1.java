package com.dk.juc.mod.disruptor.demo1;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:37
 **/
public class DisruptorMain1 {
    public static void main(String[] args) throws InterruptedException {
        Long time = System.currentTimeMillis();
        RingBuffer<TradeTransaction> ringBuffer;
        Producer producer = null;
        // 创建缓冲池
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // 创建工厂
        EventFactory<TradeTransaction> factory = new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        };
        // 创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
        int ringBufferSize = 1024 * 1024; //
        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
        // 创建ringBuffer
        ringBuffer = RingBuffer.create(ProducerType.MULTI, factory, ringBufferSize, YIELDING_WAIT);
        SequenceBarrier barriers = ringBuffer.newBarrier();
        // 创建10个消费者来处理同一个生产者发的消息(这10个消费者不重复消费消息)
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer();
        }
        WorkerPool<TradeTransaction> workerPool = new WorkerPool<TradeTransaction>(ringBuffer, barriers,
                new IntEventExceptionHandler(), consumers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(executor);

        producer = new Producer(ringBuffer);

        for (int i = 0; i < 10; i++) {
            producer.onData();
        }
        System.out.println("花费时间 :" + (System.currentTimeMillis() - time));
    }
}

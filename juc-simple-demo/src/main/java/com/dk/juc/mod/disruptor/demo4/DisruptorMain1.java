package com.dk.juc.mod.disruptor.demo4;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-08 18:51
 **/
public class DisruptorMain1 {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();

        DisDataFactory factory = new DisDataFactory();

        //the size of the ringBuffer,must be power of 2.
        int bufferSize = 1024;

        //init the disruptor
        Disruptor<DisData> disruptor = new Disruptor<DisData>(factory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy());


        //add the consumers
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer());
        disruptor.start();

        //add the producer
        RingBuffer<DisData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer buffer = ByteBuffer.allocate(8);

        //push the data to buffer
        for (long l = 0;l<10l ; l++) {
            buffer.putLong(0,l);
            producer.pushData(buffer);
            Thread.sleep(100);
            System.out.println("add data:"+l);
        }
    }
}

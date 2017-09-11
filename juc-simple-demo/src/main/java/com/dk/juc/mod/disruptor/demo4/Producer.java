package com.dk.juc.mod.disruptor.demo4;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-08 18:48
 **/
public class Producer {
    private final RingBuffer<DisData> ringBuffer;

    public Producer(RingBuffer<DisData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer buffer){
        long sequence = ringBuffer.next();//grab the next sequence
        try {
            //get the entry in the disruptor for the sequence
            DisData event = ringBuffer.get(sequence);
            event.setValue(buffer.getLong(0));//fill the data
        } finally {
            ringBuffer.publish(sequence);//publish the sequence
        }
    }
}

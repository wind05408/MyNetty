package com.dk.juc.concurrent.lockfree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA
 * CounterMain
 * 计数器 AtomicLong
 * @author dk
 * @date 2017/7/20 17:59
 */
public class CounterMain {
    private AtomicLong count = new AtomicLong();

    public void increment(){
        count.incrementAndGet();
    }
    public long getCount(){
        return count.get();
    }
}

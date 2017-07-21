package com.dk.juc.concurrent.lockfree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA
 * CounterMain2
 * LockFree算法 不需要加锁：3部分---->1.循环 2.CAS(CompareAndSet) 3.回退(break,continue)
 * @author dk
 * @date 2017/7/20 18:02
 */
public class CounterMain2 {
    private AtomicLong max = new AtomicLong();

    public void set(long value){
        for (;;){ //1.循环
           long current = max.get();
           if(value>current){
               if(max.compareAndSet(current,value)){//CAS
                   break;//回退
               }else {
                   continue;
               }

           }else {
               break;
           }
        }
    }
    public long getMax(){
        return max.get();
    }

}

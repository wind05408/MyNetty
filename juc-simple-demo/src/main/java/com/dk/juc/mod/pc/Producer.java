package com.dk.juc.mod.pc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-06 19:17
 **/
public class Producer implements Runnable {
    private volatile boolean isRunning  = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("start producer id = "+Thread.currentThread().getId());

        try {
            while (isRunning){
               Thread.sleep(r.nextInt(SLEEPTIME));
               data = new PCData(count.incrementAndGet());
               System.out.println("producer "+data+" is put into queue");
               if(!queue.offer(data,2, TimeUnit.SECONDS)){
                   System.out.println("failed to put data:"+data);
               }
            }
            System.out.println(isRunning);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isRunning = false;
    }
}

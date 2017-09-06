package com.dk.juc.mod.pc;

import java.text.MessageFormat;
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
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        System.out.println("start Consumer id = "+Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true){
                PCData data = queue.take();
                if(null != data){
                    int re = data.getIntData()*data.getIntData();
                    System.out.println(MessageFormat.format("Consumer data {0}*{1}={2}",data.getIntData(),data.getIntData(),re));
                }
                Thread.sleep(r.nextInt(SLEEPTIME));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

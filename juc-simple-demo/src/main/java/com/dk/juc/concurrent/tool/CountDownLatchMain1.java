package com.dk.juc.concurrent.tool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-04 17:39
 **/
public class CountDownLatchMain1 implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getName()+" check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10 ; i++) {
            service.submit(new CountDownLatchMain1());
        }
        end.await();
        System.out.println("Fire!!!!!!!!");
        service.shutdown();
    }
}

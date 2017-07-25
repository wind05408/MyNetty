package com.dk.juc.concurrent.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA
 * SemaphoreMain
 *
 * @author dk
 * @date 2017/7/25 15:53
 */
public class SemaphoreMain implements Runnable{

    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+":done!");
            semaphore.release();//当到达5个这个临界点
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemaphoreMain main = new SemaphoreMain();
        for (int i = 0; i < 20 ; i++) {
            exec.submit(main);
        }
        exec.shutdown();
    }
}

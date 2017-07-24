package com.dk.juc.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * ReenterLock
 *
 * @author dk
 * @date 2017/7/24 19:25
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();//重入锁 re-entrant-lock
    public static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 1000000 ; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);




    }
}

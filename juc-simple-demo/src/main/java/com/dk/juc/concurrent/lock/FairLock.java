package com.dk.juc.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * FairLock
 *
 * @author dk
 * @date 2017/7/25 11:10
 */
public class FairLock implements Runnable {
    public  static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" get the lock");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock,"Thread_1");
        Thread t2 = new Thread(fairLock,"Thread_2");
        t1.start();
        t2.start();
    }
}

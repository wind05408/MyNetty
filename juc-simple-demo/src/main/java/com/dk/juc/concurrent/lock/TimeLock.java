package com.dk.juc.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * TimeLock
 *
 *
 * @author dk
 * @date 2017/7/24 19:44
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    //由于占有锁的线程会持有锁6s,故另一个线程无法在5s的等待时间内获得锁，所以请求骑锁失败
    @Override
    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else {
                System.out.println(Thread.currentThread().getName()
                        +" get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}


//    public boolean tryLock(long timeout, TimeUnit unit)
//            throws InterruptedException {
//        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
//    }
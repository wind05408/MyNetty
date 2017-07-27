package com.dk.juc.concurrent.tool;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-07-27 14:06
 **/
public class ReadWriteLockMain {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock,int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockMain main = new ReadWriteLockMain();

        Runnable readThread = new Runnable() {
            @Override
            public void run() {
                try {
                    main.handleRead(readLock);
                    main.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Runnable writeThread = new Runnable() {
            @Override
            public void run() {
                try {
                    main.handleWrite(writeLock,new Random().nextInt());
                    main.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readThread).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeThread).start();
        }

    }



}

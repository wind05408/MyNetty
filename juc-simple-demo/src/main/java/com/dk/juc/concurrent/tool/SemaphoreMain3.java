package com.dk.juc.concurrent.tool;

import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * SemaphoreMain3
 *
 * @author dk
 * @date 2017/7/25 16:37
 */
public class SemaphoreMain3 extends Thread {
    public static void main(String[] args) {
        int i = 0;
        while (i < 50) {
            i++;
            new SemaphoreMain3().start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 控制某资源同时被访问的个数的类 控制同一时间最后只能有5个访问
     */
    static Semaphore semaphore = new Semaphore(5);
    static int timeout = 500;

    public void run() {
        try {
            Object connec = getConnection();
            System.out.println("获得一个连接" + connec);
            Thread.sleep(300);
            releaseConnection(connec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseConnection(Object connec) {
        /* 释放许可 */
        semaphore.release();
        System.out.println("释放一个连接" + connec);
    }

    public Object getConnection() {
        try {/* 获取许可 */
            boolean getAccquire = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
            if (getAccquire) {
                return UUID.randomUUID().toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("timeout");
    }
}
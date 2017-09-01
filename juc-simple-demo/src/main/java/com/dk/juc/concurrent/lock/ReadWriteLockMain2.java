package com.dk.juc.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-01 15:01
 **/
public class ReadWriteLockMain2 {
    public static void main(String[] args) {
        final QueueData queueData = new QueueData();
        for (int i = 0; i < 3 ; i++) {
           new Thread(){
               @Override
               public void run() {
                   while (true){
                       queueData.get();
                   }
               }
           }.start();
        }

        for (int i = 0; i < 3 ; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        queueData.put(new Random().nextInt(10000));
                    }
                }
            }.start();
        }
    }
}

class QueueData{
    private Object data = null;
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get(){
        rwl.readLock().lock();
        System.out.println("R:"+Thread.currentThread().getName() + " be ready to read data!");
        try {
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("R:"+Thread.currentThread().getName() + "have read data :" + data);
        rwl.readLock().unlock();
    }

    public void put(Object data){
        rwl.writeLock().lock();
        System.out.println("W:"+Thread.currentThread().getName() + " be ready to write data!");
        try {
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        System.out.println("W:"+Thread.currentThread().getName() + " have write data: " + data);
        rwl.writeLock().unlock();
    }

}

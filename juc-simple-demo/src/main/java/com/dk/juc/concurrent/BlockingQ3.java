package com.dk.juc.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * BlockingQ3
 * @see java.util.concurrent.ArrayBlockingQueue
 * @author dk
 * @date 2017/7/20 15:15
 */
public class BlockingQ3 {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;


    // 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
    public Object take() throws InterruptedException{
        lock.lock();
        //要执行await操作，必须先取得该Condition的锁，执行await操作之后，锁会释放
        //被唤醒之前，需要先获得锁
        try {
            if(linkedList.size() == 0){
                notEmpty.await();
            }
            if(linkedList.size() == maxLength){
                notFull.signalAll();
            }
            return linkedList.poll();
        } finally {
            lock.unlock();
        }

    }

    // 将指定元素插入此队列中（如果立即可行且不会违反容量限制)
    public void offer(Object object) throws InterruptedException{
        lock.lock();
        try {
            if(linkedList.size() == 0){
                notEmpty.signalAll();
            }
            if(linkedList.size() == maxLength){
                notFull.await();
            }
            linkedList.add(object);
        } finally {
            lock.unlock();
        }
    }
}

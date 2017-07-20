package com.dk.juc.concurrent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * BlockingQ2
 *
 * @author dk
 * @date 2017/7/20 15:12
 */
public class BlockingQ2 {
    private Object notEmpty = new Object();
    private Object notFull = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;


    // 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
    public Object take() throws InterruptedException{
        synchronized (notEmpty){
            if(linkedList.size() == 0){
                notEmpty.wait();
            }
            synchronized (notFull){
                if(linkedList.size() == maxLength){
                    notFull.notifyAll();
                }
                return linkedList.poll();
            }

        }
    }

    // 将指定元素插入此队列中（如果立即可行且不会违反容量限制)
    public void offer(Object object) throws InterruptedException{
        synchronized (notEmpty){
            if(linkedList.size() == 0){
                notEmpty.notifyAll();
            }
            synchronized (notFull){
                if(linkedList.size() == maxLength){
                    notFull.wait();
                }
                linkedList.add(object);
            }
        }
    }
}

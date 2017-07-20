package com.dk.juc.concurrent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * BlockingQ
 * wait notify
 *
 * @author dk
 * @date 2017/7/20 15:05
 */
public class BlockingQ {
    private Object notEmpty = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();

    // 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
    public Object take() throws InterruptedException{
        synchronized (notEmpty){
            if(linkedList.size() == 0){
                notEmpty.wait();
            }
            return linkedList.poll();
        }
    }

    // 将指定元素插入此队列中（如果立即可行且不会违反容量限制)
    public void offer(Object object) throws InterruptedException{
        synchronized (notEmpty){
            if(linkedList.size() == 0){
                notEmpty.notifyAll();
            }
            linkedList.add(object);
        }
    }

}

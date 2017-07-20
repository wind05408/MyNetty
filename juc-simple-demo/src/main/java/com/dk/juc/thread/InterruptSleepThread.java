package com.dk.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * InterruptSleepThread
 *
 * @author dk
 * @date 2017/7/20 17:30
 */
public class InterruptSleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
    }
}

/**
 *
 * 输出结果:
 Interrupted When Sleep
 interrupt:false
 */




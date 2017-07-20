package com.dk.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * InterruptThread2
 *可能会遇到处于运行期且非阻塞的状态的线程，这种情况下，直接调用Thread.interrupt()中断线程是不会得到任响应的，
 *如下代码，将无法中断非阻塞状态下的线程：
 * @author dk
 * @date 2017/7/20 17:42
 */

public class InterruptThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("未被中断");
                }
            }
        };

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}

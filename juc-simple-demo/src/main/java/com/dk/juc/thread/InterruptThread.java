package com.dk.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * InterruptThread
 *
 * @author dk
 * @date 2017/7/20 17:37
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
//                线程中断
//                已跳出循环,线程中断!
                while (true){
                    if(this.isInterrupted()){
                        System.out.println("线程中断");
                        break;
                    }
                }
                System.out.println("已跳出循环,线程中断!");
            }
        };

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}

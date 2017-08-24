package com.dk.juc.concurrent.pool;

import java.text.MessageFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 抛弃当前任务
 * @create 2017-08-24 18:40
 **/
public class DiscardPolicyMain {
    public static void main(String[] args) {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            System.out.println(MessageFormat.format("第{0}次提交任务,当前等待队列长度{1}", i, executor.getQueue()
                    .size()));
            executor.execute(new Runnable() {

                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        //logger.error("", e);
                    }
                }
            });

        }
        executor.shutdown();

    }

}
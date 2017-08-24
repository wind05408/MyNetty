package com.dk.juc.concurrent.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: CallerRunsPolicy 返回给调用者的饱和策略
 * @create 2017-08-24 18:35
 **/
public class CallerRunsPolicyMain {
    public static void main(String[] args) {
        //等待队列
        final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, queue);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 15; i++) {
            executor.execute(new Runnable() {

                public void run() {
                    System.out.println("run-" + Thread.currentThread().getName() + " queue:"
                            + queue.size());
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        //logger.error("", e);
                    }
                }
            });
        }
    }
}

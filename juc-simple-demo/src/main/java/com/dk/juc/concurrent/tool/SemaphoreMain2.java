package com.dk.juc.concurrent.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA
 * SemaphoreMain2
 *
 * @author dk
 * @date 2017/7/25 16:07
 */
public class SemaphoreMain2 {
    public static void main(String[] args) {
        //线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //只能5个线程访问
        final Semaphore semaphore = new Semaphore(5);

        //模拟20个客户端访问
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable runner = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semaphore.acquire();
                        System.out.println("Accessing:"+NO);
                        Thread.sleep((long) (Math.random()*10000));
                        // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runner);
        }
        // 退出线程池
        service.shutdown();
    }
}

package com.dk.juc.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-05 17:01
 **/
public class SynchronousQueueMain5 {

    public static void main(String[] args) throws InterruptedException {

        final SynchronousQueue<Long> workQueue = new SynchronousQueue();
        boolean offer = workQueue.offer(2L); // offer不能放入,前面无阻塞线程,本身也不阻塞不会放入到阻塞线程队列中
        System.out.println("main thread: offer=" + offer);
        Long poll = workQueue.poll(); // 不能放poll出元素为null,前面无阻塞线程,本身也不阻塞不会放入到阻塞线程队列中
        System.out.println("main thread: poll=" + poll);
        ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(4); // 内部源码实现是:new ThreadPoolExecutor(0,Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        System.out.println("/**=====================下面是队列是 take类型========*/");

        newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out
                            .println("take thread 1: begin take and thread will be blocked by call  park(await) method");
                    Long take = workQueue.take();
                    System.out
                            .println("take thread 1:   take suceffull take object="
                                    + take);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out
                            .println("take thread 2: begin take and thread will be blocked by call  park(await) method");
                    Long take = workQueue.take();
                    System.out
                            .println("take thread 2:   take suceffull take object="
                                    + take);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Thread.sleep(1000);
        poll = workQueue.poll();
        System.out.println("main thread: 队列是take类型. 同类型操作分两种:1.阻塞take会阻塞,加入队列中 2.非阻塞操作poll失败. Queue接口poll失败,  poll=" + poll);
        offer = workQueue.offer(2L);  //
        System.out.println("main thread: 队列是take类型. 非同类型不管阻塞不阻塞都成功. 非同类型非阻塞操作Queue接口 offer 成功? " + offer);
        Thread.sleep(2000);
        long object = 123L;
        System.out.println("put thead: begin put " + object);
        try {
            workQueue.put(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("put thead: 队列是take类型. 非同类型不管阻塞不阻塞都成功.  blockingQueue 阻塞接口 put调用未阻塞,调用成功 SynchronousQueue will unpark(notify) the take thread ");

        System.out.println("/**=====================下面是 Put类型========*/");

        System.out.println("/*先阻塞两个put*/");

        newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                long object = 123L;
                System.out.println("put thead 1: begin put " + object);
                try {
                    workQueue.put(object);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out
                        .println("put thead 1: finish put sucefully , SynchronousQueue will unpark(notify) the take thread ");
            }
        });

        newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                long object = 123L;
                System.out.println("put thead 2: begin put " + object);
                try {
                    workQueue.put(object);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put thead 2: finish put sucefully , SynchronousQueue will unpark(notify) the take thread ");
            }
        });


        Thread.sleep(2000);
        System.out.println("/*试图offer*/");
        offer = workQueue.offer(2L);  //
        System.out.println("main thread: 队列是put类型.  同类型操作分两种:1.阻塞put会阻塞,加入队列中 2.非阻塞操作offer失败.  非阻塞操作Queue接口 offer 成功? " + offer);

        poll = workQueue.poll();
        System.out.println("main thread: 队列是put类型. 非同类型不管阻塞不阻塞都成功.  poll=" + poll);

        Long take = workQueue.take();
        System.out
                .println("main thread: 队列是put类型. 非同类型不管阻塞不阻塞都成功. BlockingQueue阻塞接口take调用未阻塞, take suceffull take object="
                        + take);
        newCachedThreadPool.shutdown();
    }
}

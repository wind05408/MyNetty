package com.dk.juc.concurrent.tool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: Future
 * @create 2017-07-26 19:29
 **/
public class FutureMain2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}

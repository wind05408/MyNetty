package com.dk.juc.concurrent;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA
 * ExecutorsMain
 *
 * @author dk
 * @date 2017/7/20 11:20
 */
public class ExecutorsMain
{
    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        Callable<Object> task = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Object result ="ThreadName:"+Thread.currentThread().getName();
                return result;
            }
        };

        Future<Object> future = executor.submit(task);
        try {
            System.out.println(future.get().toString());
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();


    }
}

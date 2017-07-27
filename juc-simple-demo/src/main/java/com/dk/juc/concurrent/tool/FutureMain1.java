package com.dk.juc.concurrent.tool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with MyNetty.
 *
 * @author dk05408
 * @Description:
 * @create 2017-07-26 19:29
 **/
public class FutureMain1 {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };

        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);
            System.out.println(String.valueOf(future.get()));
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
    }
}

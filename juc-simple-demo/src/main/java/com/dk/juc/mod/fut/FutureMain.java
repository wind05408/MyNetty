package com.dk.juc.mod.fut;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-12 16:08
 **/
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new RealData2("a"));

        ExecutorService service = Executors.newFixedThreadPool(1);

        service.submit(futureTask);

        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据="+futureTask.get());
        service.shutdown();
    }
}

package com.dk.juc.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-04 14:51
 **/
public class ThreadLocalMain {
    public  static final int GEN_COUNT = 10000000;
    public  static final int THREAD_COUNT = 4;

    static ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tand = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long>{
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if(mode == 0){
                return rnd;
            }else if(mode == 1){
                return tand.get();
            }else {
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            Long b = System.currentTimeMillis();
            for (int i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            Long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" speed "+(e-b)+"ms");
            return e-b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futs = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT ; i++) {
            futs[i] = service.submit(new RndTask(0));
        }
        long totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("多线程访问同一个Random实例："+totaltime+"ms");

        for (int i = 0; i < THREAD_COUNT ; i++) {
            futs[i] = service.submit(new RndTask(1));
        }
        totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("使用ThreadLocal同一个Random实例："+totaltime+"ms");
        service.shutdown();
    }

}

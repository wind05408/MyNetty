package com.dk.juc.java8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 15:27
 **/
public class AtomicMain {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 100000000;

    private AtomicLong account = new AtomicLong(0l);
    private LongAdder laccount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    protected synchronized long inc(){
        return ++count;
    }

    protected synchronized long getCount(){
        return count;
    }

    public class SyncThread implements Runnable{
        protected String name;
        protected long startTime;
        AtomicMain out;

        public SyncThread(long startTime, AtomicMain out) {
            this.startTime = startTime;
            this.out = out;
        }


        @Override
        public void run() {
            long v = out.getCount();
            while (v<TARGET_COUNT){
                v = out.inc();
            }
            long  endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:"+(endTime-startTime)+"ms v="+v);
            cdlsync.countDown();
        }
    }

    public class AtomicThread implements Runnable{
        protected String name;
        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = account.get();
            while (v<TARGET_COUNT){
                v = account.incrementAndGet();
            }
            long  endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:"+(endTime-startTime)+"ms v="+v);
            cdlatomic.countDown();
        }
    }

    public class LongAdderThread implements Runnable{
        protected String name;
        protected long startTime;

        public LongAdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = laccount.sum();
            while (v<TARGET_COUNT){
                laccount.increment();
                v = laccount.sum();
            }
            long  endTime = System.currentTimeMillis();
            System.out.println("LongAdderThread spend:"+(endTime-startTime)+"ms v="+v);
            cdladdr.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(startTime,this);
        for (int i = 0; i < TASK_COUNT; i++) {
            service.submit(sync);
        }
        cdlsync.await();
        service.shutdown();
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            service.submit(atomicThread);
        }
        cdlatomic.await();
        service.shutdown();
    }

    public void testLongAdder() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAdderThread longAdderThread = new LongAdderThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            service.submit(longAdderThread);
        }
        cdladdr.await();
        service.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicMain main  = new AtomicMain();
        main.testSync();
        main.testAtomic();
        main.testLongAdder();
    }
}

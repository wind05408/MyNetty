package com.dk.juc.mod.disruptor;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: CPU Cache的优化：解决伪共享问题，缓存行
 * @create 2017-09-11 18:07
 **/
public final class FalseSharingMain1 implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERACIONS = 500L*1000L*1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharingMain1(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }


    @Override
    public void run() {
        long i = ITERACIONS+1;
        while ( 0 != --i){
            longs[arrayIndex].value = i;//缓存行
        }

    }
    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingMain1(i));
        }

        for (Thread t:threads) {
            t.start();
        }
        for (Thread t:threads) {
            t.join();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration="+(System.currentTimeMillis()-start));
    }

    private final static class VolatileLong {
        public volatile  long value = 0L;
       public long p1,p2,p3,p4,p5,p6,p7;
    }
}

package com.dk.juc.mod.ps;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-21 15:17
 **/
public class PSSort {
    static int exchFlag = 1;
    static ExecutorService pool= Executors.newCachedThreadPool();
    static int[] arr={1,4,2,6,35,3};

    public static synchronized int getExchFlag() {
        return exchFlag;
    }

    public static synchronized void setExchFlag(int v) {
        PSSort.exchFlag = v;
    }

    public static class  OddEventSortTask implements Runnable{
        int i;
        CountDownLatch latch;

        public OddEventSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if(arr[i]>arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEventSort(int[] array) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1){
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(array.length/2-(array.length%2==0?start:0));
            for (int i = start;i<array.length -1;i+=2){
                pool.submit(new OddEventSortTask(i,latch));
            }
            latch.await();

            System.out.println(Arrays.toString(array));
            if(start == 0){
                start = 1;
            }else {
                start = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        pOddEventSort(arr);
        for (int ar:arr) {
            System.out.println(ar);
        }
        pool.shutdown();
    }
}

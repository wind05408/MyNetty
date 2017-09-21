package com.dk.juc.mod.ps;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  插入排序,假设将无序的数据插入有序的数据.从此一个排序就完成了;
 * 一般我将第一个数据定义为已经排序好的数据
 * @create 2017-09-21 15:42
 **/
public class InsertSortMain {
    static int[] arr = {1, 4, 2, 5, 6, 3};

    public static void insertSort(int[] arr) {
        int length = arr.length;
        int j, i, key;
        for (i = 0; i < length; i++) {
            //为key 准备插入的元素
            key = arr[i];
            j = i - 1;
            //如果前置大于后值:大值后移
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            //找到合适的位置,插入key
            arr[j + 1] = key;
        }
    }

    /**
     * 希尔排序(分组排序)
     *
     * @param arr 排序真谛:
     *            1'h为子组的大小,表示每次与之对比的值,在几个h之后,最后h为1
     *            2'将a[i]<a[i-h] 值,前值小于后值,执行大值放入a[]
     */
    public static void shellSort(int[] arr) {
        //计算出最大的h值
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                //后值大于前值
                if (arr[i] < arr[i - h]) {
                    //将较小值放入tmp
                    int tmp = arr[i];
                    //用现在i值减去初始i值,得到j
                    int j = i - h;
                    //如果j大于零,a[j]>tmp
                    while (j >= 0 && arr[j] > tmp) {
                        //小值放入a[j+h]
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    //将大值放入j+h
                    arr[j + h] = tmp;
                }
            }
            //计算出下一个h值
            h = (h - 1) / 3;
        }
    }

    ////////////////希尔算法的并行程序
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static class ShellSortTask implements Runnable {
        int i = 0;
        int h = 0;
        CountDownLatch l;

        public ShellSortTask(int i, int h, CountDownLatch lathc) {
            this.i = i;
            this.h = h;
            this.l = lathc;
        }

        public void run() {
            if (arr[i] < arr[i - h]) {
                int tmp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = tmp;
            }
            l.countDown();
        }
    }

    public static void pShellSort(int[] arr) throws InterruptedException {
        //计算出最大的n值
        int h = 1;
        CountDownLatch lathc = null;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("h=" + h);
            if (h >= 4) {
                lathc = new CountDownLatch(arr.length - h);
            }
            for (int i = h; i < arr.length; i++) {
                //控制线程数量
                if (h >= 4) {
                    pool.submit(new ShellSortTask(i, h, lathc));
                } else {
                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = tmp;
                    }
                    System.out.println(Arrays.toString(arr));
                }
            }
            //等等线程排序完成,进入下一次排序
            lathc.await();
            //计算下一个h值
            h = (h - 1) / 3;
        }


    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.toString(arr));
        pShellSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
        pool.shutdown();
    }
}
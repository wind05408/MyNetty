package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * SynchronizedMain
 *
 * @author dk
 * @date 2017/6/23 18:52
 */
public class SynchronizedMain implements Runnable {
    public static Integer i =0;
    static SynchronizedMain instance = new SynchronizedMain();
    @Override
    public void run() {
        for (int j = 0; j <10000000 ; j++) {
//            synchronized (i){
            synchronized (instance){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);

    }
}

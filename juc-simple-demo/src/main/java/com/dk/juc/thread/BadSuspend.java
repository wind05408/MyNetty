package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * BadSuspend
 *
 * @author dk
 * @date 2017/6/23 14:45
 */
public class BadSuspend {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new  ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new  ChangeObjectThread("t2");

    private static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();

    }
}

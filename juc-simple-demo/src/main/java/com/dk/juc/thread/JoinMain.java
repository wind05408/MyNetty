package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * JoinMain
 *
 * @author dk
 * @date 2017/6/23 16:42
 */
public class JoinMain {
    public volatile static int i =0;

    private static class AddThread extends Thread{
        @Override
        public void run() {
            for (i=0;i<9876543;i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread add = new AddThread();
        add.start();
        add.join();
        System.out.println(i);
    }
}

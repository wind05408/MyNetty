package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * ThreadGroupMain
 * 线程组ThreadGroup
 * @author dk
 * @date 2017/6/23 18:30
 */
public class ThreadGroupMain implements Runnable {

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");

        Thread t1 = new Thread(tg,new ThreadGroupMain(),"t1");
        Thread t2 = new Thread(tg,new ThreadGroupMain(),"t2");

        t1.start();
        t2.start();

        System.out.println(tg.activeCount());
        tg.list();

    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() +
                "-"+Thread.currentThread().getName();
        while (true){
            System.out.println("I am "+groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * DaemonMain
 * 守护线程Daemon
 * @author dk
 * @date 2017/6/23 18:37
 */
public class DaemonMain {
    private static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);//设置t为守护线程
        t.start();

        //当主线程main为用户线程，因此在main线程休眠2s后退出，整个程序也随之结束
        //如果不把t设置为守护线程，main线程结束后，t线程还不会不停打印，永远不会结束
        Thread.sleep(2000);
    }
}

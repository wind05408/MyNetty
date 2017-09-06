package com.dk.juc.concurrent.lock;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-06 16:55
 **/
public class DeadLockMain extends Thread {
    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLockMain(Object tool) {
        this.tool = tool;
        if(tool == fork1){
            this.setName("哲学家A");
        }
        if(tool == fork2){
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
       if(tool == fork1){
            synchronized (fork1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2){
                    System.out.println("哲学家A开始吃饭了");
                }
            }
        }

        if(tool == fork2){
            synchronized (fork2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1){
                    System.out.println("哲学家B开始吃饭了");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockMain 哲学家A= new DeadLockMain(fork1);
        DeadLockMain 哲学家B= new DeadLockMain(fork2);
        哲学家A.start();
        哲学家B.start();
        Thread.sleep(1000);
    }
}

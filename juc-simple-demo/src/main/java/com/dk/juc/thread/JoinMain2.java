package com.dk.juc.thread;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-18 15:32
 **/
public class JoinMain2 {
    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        joinThread.setName("joinThread");
        joinThread.start();
        System.out.println("Main Thread begin");
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread finish");
    }

    private static class JoinThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +" begin  the work");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +" finish  the work");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//console
// join
//        Main Thread begin
//        joinThread begin  the work
//        joinThread finish  the work
//        Main Thread finish
//        没有join
//        Main Thread begin
//        Main Thread finish
//        joinThread begin  the work
//        joinThread finish  the work

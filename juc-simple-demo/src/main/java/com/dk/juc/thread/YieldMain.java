package com.dk.juc.thread;

/**
 * Created with IntelliJ IDEA
 * YieldMain
 *
 * @author dk
 * @date 2017/6/23 16:54
 */
public class YieldMain {
    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

        producer.start();
        consumer.start();

    }

    private static class Producer extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        }
    }

    private static class Consumer extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Consumer : Consumed Item " + i);
                Thread.yield();
            }
        }
    }
}

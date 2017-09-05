package com.dk.juc.concurrent;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-05 16:07
 **/
public class SynchronousQueueMain {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        for(int i=0;i<10;i++)
            new Thread(new ThreadProducer(queue)).start();
        for(int i=0;i<10;i++)
            new Thread(new ThreadConsumer(queue)).start();
    }
    static class  ThreadProducer implements Runnable {
        private SynchronousQueue<String> queue;
        static int cnt = 0;

        public ThreadProducer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String name = "";
            int val = 0;
            Random random = new Random(System.currentTimeMillis());
            while (true) {
                cnt = (cnt + 1) & 0xFFFFFFFF;

                try {
                    val = random.nextInt() % 15;
                    if (val < 5) {
                        name = "offer name:" + cnt;
                        queue.offer(name);
                    } else if (val < 10) {
                        name = "put name:" + cnt;
                        queue.put(name);
                    } else {
                        name = "offer wait time and name:" + cnt;
                        queue.offer(name, 1000, TimeUnit.MILLISECONDS);
                    }
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class ThreadConsumer implements Runnable{
        private SynchronousQueue<String> queue;

        public ThreadConsumer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String name;
            while (true){
                try {
                    name = queue.take();
                    System.out.println("ThreadConsumer take "+name);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

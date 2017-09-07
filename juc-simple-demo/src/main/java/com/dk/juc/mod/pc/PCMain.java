package com.dk.juc.mod.pc;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 生产者-消费者模式 ===>数据在多线程间的共享
 * @create 2017-09-06 19:19
 **/
public class PCMain {
    public static void main(String[] args) throws InterruptedException {
        //BlockingQueue共享缓冲区：缓存生产者提交的任务或数据，供消费者使用
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);

        //生产者：创建和提交数据或任务
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        //消费者：处理和消费数据
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        Thread.sleep(10*1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3*1000);
        service.shutdown();

    }
}

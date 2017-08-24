package com.dk.juc.concurrent.pool;


import java.util.concurrent.*;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  当提交的任务无法进入等待队列且线程池中创建的线程数量已经达到了最大线程数量的限制，则会拒绝新提交的任务。
 * @create 2017-08-24 18:36
 **/
public class AbortPolicyMain {
    public static void main(String[] args) {

        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        executor.setThreadFactory(new MyThreadFactory("Test"));

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new Runnable() {

                    public void run() {
                        //doNothing
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("第" + i + "次提交线程被拒绝!  当前活动线程数：" + executor.getActiveCount()
                        + " 队列长度：" + executor.getQueue().size());
            }
        }
    }

    private static class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            System.out.println("create "+name+"_"+thread.getId());
            return thread;
        }

        private String name;

        public MyThreadFactory(String name) {
            this.name = name;
        }
    }
}

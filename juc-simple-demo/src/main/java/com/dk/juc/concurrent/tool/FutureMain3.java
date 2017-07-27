package com.dk.juc.concurrent.tool;

import java.util.concurrent.*;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: CompletionService.class 执行多个带返回值的任务，并取得多个返回值
 * @create 2017-07-27 15:58
 **/
public class FutureMain3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        CompletionService<Integer> cs = new ExecutorCompletionService(threadPool);

        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return taskId;
                }
            });
        }
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();
    }
}

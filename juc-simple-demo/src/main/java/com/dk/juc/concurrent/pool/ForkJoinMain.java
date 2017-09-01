package com.dk.juc.concurrent.pool;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-29 14:49
 **/
public class ForkJoinMain extends RecursiveTask<Integer> {
    private static  final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJoinMain(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end-start)<= THRESHOLD;
        if(canCompute){
            for (int i = start; i <= end ; i++) {
                sum+=i;
            }
        }else {
            int middle = (start+end)/2;
            ForkJoinMain leftTask = new ForkJoinMain(start,middle);
            ForkJoinMain rightTask = new ForkJoinMain(middle,end);
            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult+rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinMain task = new ForkJoinMain(1,1000000000);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

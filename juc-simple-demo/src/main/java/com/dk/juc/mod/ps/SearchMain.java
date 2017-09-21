package com.dk.juc.mod.ps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 适用场景:有很多系统变量需要查询,或者查询redis这样数据量大得list web适用场景比较少,但是作为平台软件是很常用的
 * @create 2017-09-21 15:36
 **/
public class SearchMain {

    //定义我们需要查询的无序数组
    static int[] arr={1,22,2,3,4,5,344,6,7,8,10,9};
    //定义线程池数据,已经存放结果的Result
    static ExecutorService pool= Executors.newCachedThreadPool();
    static final int thread_num=2;
    static AtomicInteger result=new AtomicInteger(-1);//初始值定位-1

    public static int search(int searchValue ,int beginPos,int endPos){
        int j=0;
        for (j = beginPos; j <endPos ; j++) {
            if (result.get()>=0){
                return result.get();
            }

            if (arr[j]==searchValue){
                //如果设置失败,表示其他现场已经先找到了
                if (!result.compareAndSet(-1,j)){
                    //如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
                    //-1当前值为-1就返回
                    return result.get();
                }
                return j;
            }

        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{
        int begin,end,searchValue;
        public SearchTask(int searchValue,int begin,int end){
            this.begin=begin;
            this.end=end;
            this.searchValue=searchValue;
        }

        public Integer call() throws Exception {
            int re=search(searchValue,begin,end);
            return re;
        }
    }

    public static int psearch(int searchValue) throws InterruptedException,ExecutionException{
        int subArrSize=arr.length/thread_num+1;
        List<Future<Integer>> re=new ArrayList<Future<Integer>>();

        for (int i = 0; i <arr.length ; i+=subArrSize) {
            int end=i+subArrSize;
            if (end>=arr.length) end=arr.length;
            re.add(pool.submit(new SearchTask(searchValue,i,end)));
        }
        for (Future<Integer> fu:
                re) {
            if (fu.get()>=0) return (fu.get());

        }
        return  -1;
    }

    public static void main(String[] args) {
        try {
            System.out.println(psearch(9));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}

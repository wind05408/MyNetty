package com.dk.juc.concurrent;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-05 16:17
 **/
public class TransferQueueMain2 {

    private static TransferQueue<String> queue = new LinkedTransferQueue<String>();

    public static void main(String[] args) throws Exception {

        new Productor(1).start();

        Thread.sleep(100);

        System.out.println("over.size=" + queue.size());//1

        Thread.sleep(1500);

        System.out.println("over.size=" + queue.size());//0
    }

    static class Productor extends Thread {
        private int id;

        public Productor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                String result = "id=" + this.id;
                System.out.println("begin to produce." + result);
                queue.tryTransfer(result, 1, TimeUnit.SECONDS);
                System.out.println("success to produce." + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

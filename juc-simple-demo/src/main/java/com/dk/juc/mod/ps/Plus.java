package com.dk.juc.mod.ps;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-12 16:39
 **/
public class Plus implements Runnable {
    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true){
            try {
                Msg msg = queue.take();
                msg.j = msg.i+msg.j;
                Multiply.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.dk.juc.mod.ps;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-12 16:41
 **/
public class Multiply implements Runnable {
    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true){
            try {
                Msg msg = queue.take();
                msg.i = msg.i*msg.j;
                Div.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

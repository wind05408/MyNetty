package com.dk.juc.mod.ps;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 并行流水线
 * @create 2017-09-12 16:44
 **/
public class PStreamMain {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 0; i < 1000 ; i++) {
            for (int j = 0; j < 1000 ; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "(("+i+"+"+j+")*"+i+")/2";
                Plus.queue.add(msg);
            }
        }
    }
}

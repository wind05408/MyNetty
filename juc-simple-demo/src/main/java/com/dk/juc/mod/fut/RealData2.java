package com.dk.juc.mod.fut;

import java.util.concurrent.Callable;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-12 16:07
 **/
public class RealData2 implements Callable<String> {

    private String para;

    public RealData2(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

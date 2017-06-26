package com.dk.juc.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * DaemonMain
 * 守护线程Daemon
 * @author dk
 * @date 2017/6/23 18:37
 */
public class DaemonMain {

    private static Logger logger = LoggerFactory.getLogger(DaemonMain.class);

    private static class DaemonT extends Thread{
        private Logger logger = LoggerFactory.getLogger(getClass());
        @Override
        public void run() {
            while (true){
                logger.info("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);//设置t为守护线程
        t.start();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element:stackTrace) {
            logger.info("_______"+element.toString());
        }

        //当主线程main为用户线程，因此在main线程休眠2s后退出，整个程序也随之结束
        //如果不把t设置为守护线程，main线程结束后，t线程还不会不停打印，永远不会结束
        Thread.sleep(2000);
    }
}

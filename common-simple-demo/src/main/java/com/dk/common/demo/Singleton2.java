package com.dk.common.demo;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 双重检查锁定
 * @create 2017-08-23 16:26
 **/
public class Singleton2 {

    private Singleton2() {}

    private static Singleton2 singleton=null;

    public static Singleton2 getInstance() {
        if (singleton == null) {
            synchronized (Singleton2.class){
                if(singleton == null){
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}

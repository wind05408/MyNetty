package com.dk.common.demo;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-23 15:44
 **/
public class Singleton {
    private Singleton(){
    }

    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

    public static final Singleton getInstance() {
        return SingletonHolder.instance;
    }
}

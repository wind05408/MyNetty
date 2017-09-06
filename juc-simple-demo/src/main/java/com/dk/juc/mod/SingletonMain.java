package com.dk.juc.mod;


/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 单例模式
 * @create 2017-09-06 18:33
 **/
public class SingletonMain {
    private SingletonMain() {
        System.out.println("SingletonMain instance create");
    }

    private static class SingletonMainHolder{
        private static final SingletonMain instance = new SingletonMain();
    }

    public static SingletonMain getInstance(){
        return SingletonMainHolder.instance;
    }

    public static void main(String[] args) {
        SingletonMain main = SingletonMain.getInstance();
        SingletonMain main2 = SingletonMain.getInstance();
        SingletonMain main3 = SingletonMain.getInstance();
        SingletonMain main4 = SingletonMain.getInstance();
    }
}

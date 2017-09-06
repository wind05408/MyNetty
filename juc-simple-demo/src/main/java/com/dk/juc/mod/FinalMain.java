package com.dk.juc.mod;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 不变模式--->java.lang.{@link String},java.lang.{@link Float},java.lang.{@link Double}
 * @create 2017-09-06 19:01
 **/
public final class FinalMain {
    private final String no;
    private final String name;
    private final int age;

    public FinalMain(String no, String name, int age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

package com.dk.juc.mod.disruptor.demo3;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:37
 **/
public class TestObject {
    public long value;

    public TestObject() {
    };

    public TestObject(long value) {
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}

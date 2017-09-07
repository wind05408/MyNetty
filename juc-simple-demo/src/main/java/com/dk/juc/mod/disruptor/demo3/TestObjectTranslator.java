package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:42
 **/
public class TestObjectTranslator implements EventTranslator<TestObject> {

    private Random random = new Random();

    @Override
    public void translateTo(TestObject event, long sequence) {
        this.generateTestObject(event);
    }

    private TestObject generateTestObject(TestObject testObject) {
        testObject.setValue(random.nextLong()%10000);
        return testObject;
    }
}

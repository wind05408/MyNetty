package com.dk.juc.mod.disruptor.demo4;

import com.lmax.disruptor.EventFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-08 18:48
 **/
public class DisDataFactory implements EventFactory<DisData> {
    @Override
    public DisData newInstance() {
        return new DisData();
    }
}

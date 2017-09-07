package com.dk.juc.mod.disruptor.demo3;

import com.lmax.disruptor.EventFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 16:37
 **/
public class TestObjectFactory implements EventFactory<TestObject>{
    /**
     * 仅仅是返回slot中，需要放置的Object，方便new disruptor的时候 fill
     * /@see Sequencer#fill()
     * @return
     */
    @Override
    public TestObject newInstance() {
        return new TestObject();
    }
}

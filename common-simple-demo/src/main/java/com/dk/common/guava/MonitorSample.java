package com.dk.common.guava;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * MonitorSample
 *
 * @author dk
 * @date 2017/7/10 15:50
 */
public class MonitorSample {

    private List<String> list = new ArrayList<String>();
    private static final int MAX_SIZE = 10;
    private Monitor monitor = new Monitor();

    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity); //Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }

}

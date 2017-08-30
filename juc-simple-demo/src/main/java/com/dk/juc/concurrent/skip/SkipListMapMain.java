package com.dk.juc.concurrent.skip;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: skipList sort
 * @create 2017-08-30 17:41
 **/
public class SkipListMapMain {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 5 ; i++) {
            map.put(i,i);
        }

        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}

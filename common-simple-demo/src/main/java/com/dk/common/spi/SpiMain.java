package com.dk.common.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-26 14:48
 **/
public class SpiMain {
    public static void main(String[] args) {
        ServiceLoader<IA> spiLoader = ServiceLoader.load(IA.class);
        Iterator<IA> iaIterator = spiLoader.iterator();
        while (iaIterator.hasNext()){
            iaIterator.next().doIt();
        }
    }
}

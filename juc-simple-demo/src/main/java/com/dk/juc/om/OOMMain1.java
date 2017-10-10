package com.dk.juc.om;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  -Xmx12m --> Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @create 2017-10-09 19:19
 **/
public class OOMMain1 {
    static final int SIZE=2*1024*1024;
    public static void main(String[] a) {
        int[] i = new int[SIZE];
    }
}


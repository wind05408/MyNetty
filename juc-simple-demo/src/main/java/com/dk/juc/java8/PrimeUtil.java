package com.dk.juc.java8;

import java.util.stream.IntStream;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 14:57
 **/
public class PrimeUtil {
    public static boolean isPrime(int number){
        int tmp = number;
        if(tmp<2){
            return false;
        }
        for (int i = 2; Math.sqrt(tmp)  >= i; i++) {
            if(tmp % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long t = IntStream.range(1,10000).filter(PrimeUtil::isPrime).count();
        long t2 = IntStream.range(1,10000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println(t);
    }

}

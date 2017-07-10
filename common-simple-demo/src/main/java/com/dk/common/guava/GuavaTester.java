package com.dk.common.guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA
 * GuavaTester
 *
 * @author dk
 * @date 2017/7/10 14:38
 */
public class GuavaTester {
    public static void main(String[] args) {

        Integer invalidInput = null;

        //Preconditions.checkNotNull(invalidInput,"invalidInput is null");

        Optional<Integer> a =  Optional.of(invalidInput);//Exception
        Optional<Integer> b =  Optional.of(new Integer(10));
        System.out.println(sum(a,b));

    }

    public static Integer sum(Optional<Integer> a,Optional<Integer> b){
        return a.get()+b.get();
    }
}

//    Exception in thread "main" java.lang.NullPointerException
//        at com.google.common.base.Preconditions.checkNotNull(Preconditions.java:770)
//        at com.google.common.base.Optional.of(Optional.java:105)
//        at com.dk.common.guava.GuavaTester.main(GuavaTester.java:16)


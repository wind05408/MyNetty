package com.dk.akka.pso;

import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 17:02
 **/
public class Fitness {
    public static double fitness(List<Double> x){
        double sum = 0;
        for (int i = 1; i < x.size(); i++) {
            sum +=Math.sqrt(x.get(i));
        }
        return sum;
    }
}

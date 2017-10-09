package com.dk.akka.pso;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 17:04
 **/
public class Bird extends UntypedActor {
    private PsoValue pBest = null;
    private PsoValue gBest = null;
    private List<Double> velocity = new ArrayList<>(5);
    private List<Double> x = new ArrayList<>(5);
    private Random r = new Random();

    @Override
    public void preStart() throws Exception {
        for (int i = 0; i <5 ; i++) {
            velocity.add(Double.NEGATIVE_INFINITY);
            x.add(Double.NEGATIVE_INFINITY);
        }

        x.set(1, (double) r.nextInt(401));

        double max = 400 -1.1*x.get(1);
        if(max<0) max = 0;
        x.set(2,r.nextDouble()*max);

        max = 484 -1.21*x.get(1)-1.1*x.get(2);
        if(max<0) max = 0;
        x.set(3,r.nextDouble()*max);

        max = 532.4 -1.331*x.get(1)-1.21*x.get(2)-1.1*x.get(3);
        if(max<0) max = 0;
        x.set(4,r.nextDouble()*max);

        double newFit = Fitness.fitness(x);
        pBest = new PsoValue(newFit,x);
        PBestMsg pBestMsg = new PBestMsg(pBest);
        ActorSelection selection = getContext().actorSelection("/user/masterbird");
        selection.tell(pBestMsg,getSelf());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof GBestMsg){
            gBest = ((GBestMsg)msg).getValue();
            for (int i = 0; i < velocity.size() ; i++) {
                updateVelocity(i);
            }
            for (int i = 0; i < x.size() ; i++) {
                updateX(i);
            }
            validateX();

            double newFit = Fitness.fitness(x);
            if(newFit > pBest.value){
                pBest = new PsoValue(newFit,x);
                PBestMsg pBestMsg = new PBestMsg(pBest);
                getSender().tell(pBestMsg,getSelf());
            }
        }else {
            unhandled(msg);
        }

    }

    private void validateX() {
        if(x.get(1) > 400){
            x.set(1, (double) r.nextInt(401));
        }


        double max = 400 -1.1*x.get(1);
        if(x.get(2) > max || x.get(2)<0){
            x.set(2,r.nextDouble()*max);
        }


        max = 484 -1.21*x.get(1)-1.1*x.get(2);
        if(x.get(3) > max || x.get(3)<0){
            x.set(3,r.nextDouble()*max);
        }


        max = 532.4 -1.331*x.get(1)-1.21*x.get(2)-1.1*x.get(3);
        if(x.get(4) > max || x.get(4)<0){
            x.set(4,r.nextDouble()*max);
        }
    }

    private void updateX(int i) {
        double newX = x.get(i)+velocity.get(i);
        x.set(i,newX);
    }

    private void updateVelocity(int i) {
        double v= Math.random()*velocity.get(i)
                +2*Math.random()*(pBest.getX().get(i)-x.get(i))
                +2*Math.random()*(gBest.getX().get(i)-x.get(i));
        v = v>0?Math.max(v,5):Math.max(v,-5);
        velocity.set(i,v);
    }
}

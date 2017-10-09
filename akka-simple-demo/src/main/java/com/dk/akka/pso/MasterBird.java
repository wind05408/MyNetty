package com.dk.akka.pso;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 17:17
 **/
public class MasterBird extends UntypedActor {
    private PsoValue gBest = null;

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof PBestMsg){
            PsoValue  pBest= ((PBestMsg)msg).getValue();
            if(gBest == null||gBest.value<pBest.value){
                System.out.println(msg+"\n");
                gBest = pBest;
                ActorSelection selection = getContext().actorSelection("/user/bird_");
                selection.tell(new GBestMsg(gBest),getSelf());
            }
        }else {
            unhandled(msg);
        }
    }
}

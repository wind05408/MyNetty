package com.dk.akka;

import akka.actor.UntypedActor;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 16:08
 **/
public class Greeter extends UntypedActor {
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg == Msg.GREET){
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE,getSelf());
        }else {
            unhandled(msg);
        }
    }
}

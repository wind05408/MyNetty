package com.dk.akka.unmodifiable;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 17:59
 **/
public class MyWork extends UntypedActor {

    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static enum Msg{
        WORKING, DONE, CLOSE;
    }

    @Override
    public void preStart() {
        logger.info("myWork starting.");
    }

    @Override
    public void postStop() throws Exception {
        logger.info("myWork stoping..");
    }

    @Override
    public void onReceive(Object msg) {
        try {
            if(msg == Msg.WORKING){
                logger.info("i am  working");
            }else if(msg == Msg.DONE){
                logger.info("stop  working");
            }else if(msg == Msg.CLOSE){
                logger.info("stop  close");
                getSender().tell(Msg.CLOSE, getSelf());
                getContext().stop(getSelf());
            }else {
                unhandled(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

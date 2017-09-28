package com.dk.akka.future;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-28 15:36
 **/
public class WorkerActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("akka.future.WorkerActor.onReceive:" + o);

        if (o instanceof Integer) {
            Thread.sleep(1000);
            int i = Integer.parseInt(o.toString());
            getSender().tell(i*i, getSelf());
        } else {
            unhandled(o);
        }
    }
}

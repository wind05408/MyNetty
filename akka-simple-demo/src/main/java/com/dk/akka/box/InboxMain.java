package com.dk.akka.box;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: Inbox 消息收件箱来给某个actor发消息，并且可以进行交互
 * @create 2017-09-27 19:24
 **/
public class InboxMain extends UntypedActor {
    private final LoggingAdapter logger = Logging.getLogger(getContext().system(),this);

    public enum Msg{
        WORKING,DONE,CLOSE;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if(o == Msg.WORKING){
            logger.info("I am  working.");
        } else if(o == Msg.DONE){
            logger.info("I am  done.");
        }else if(o == Msg.CLOSE){
            logger.info("I am  close.");
            getSender().tell(Msg.CLOSE,getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(o);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("sampleHello.config"));
        ActorRef inboxMain = system.actorOf(Props.create(InboxMain.class),"InboxMain");

        Inbox inbox = Inbox.create(system);
        inbox.watch(inboxMain);
        inbox.send(inboxMain,Msg.WORKING);
        inbox.send(inboxMain,Msg.DONE);
        inbox.send(inboxMain,Msg.CLOSE);

        while (true){
            try {
                Object receive = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
                if(receive == Msg.CLOSE){
                    System.out.println("inboxMain actor is closing");
                }else if(receive instanceof Terminated){
                    System.out.println("inboxTextActor is closed");
                    system.shutdown();
                    break;
                }else {
                    System.out.println(receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

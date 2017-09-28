package com.dk.akka.procedure;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import com.typesafe.config.ConfigFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 在actor运行过程中，可能会有多种状态，各个状态间可能会存在切换的情况，akka已经帮我们考虑到这种情况情况的处理:Procedure.
下面模拟一个婴儿。婴儿有两种不同的状态，开心和生气，婴儿有个特点就是好玩，永远不会累，所以让其睡觉婴儿就会生气，让他继续玩就会很高兴。
 * @create 2017-09-28 14:22
 **/
public class ProcedureMain  extends UntypedActor {
    private final LoggingAdapter logger = Logging.getLogger(getContext().system(),this);

    public enum Msg{
        SLEEP,PLAY;
    }

    Procedure<Object> happy = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            logger.info("I am happy! "+o);
            if(o == Msg.PLAY){
                getSender().tell("I am already happy!",getSelf());
                logger.info("I am already happy!");
            }else if (o == Msg.SLEEP) {
                logger.info("i do not like sleep!");
                getContext().become(angray);
            } else {
                unhandled(o);
            }
        }
    };

    Procedure<Object> angray = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            logger.info("i am angray! "+o);
            if(o ==Msg.SLEEP){
                getSender().tell("i am alrady angray!!", getSelf());
                logger.info("i am alrady angray!!");
            } else if(o ==Msg.PLAY) {
                logger.info("i like play.");
                getContext().become(happy);
            } else {
                unhandled(o);
            }
        }
    };

    @Override
    public void onReceive(Object o) throws Exception {
        logger.info("onReceive msg: " + o);
        if(o == Msg.SLEEP){
            getContext().become(angray);
        }else if(o == Msg.PLAY){
            getContext().become(happy);
        }else {
            unhandled(o);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("sampleHello.config"));
        ActorRef procedureMain = system.actorOf(Props.create(ProcedureMain.class), "ProcedureMain");

        procedureMain.tell(Msg.PLAY, ActorRef.noSender());
        procedureMain.tell(Msg.SLEEP, ActorRef.noSender());
        procedureMain.tell(Msg.PLAY, ActorRef.noSender());
        procedureMain.tell(Msg.PLAY, ActorRef.noSender());

        procedureMain.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}

package com.dk.akka.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description: 和java线程中的future挺像的，可以将一个actor的返回结果重定向到另一个actor中进行处理，主actor或者进程无需等待actor的返回结果。
 * @create 2017-09-28 15:35
 **/
public class FutureMain {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("sampleHello.config"));
        ActorRef printActor = system.actorOf(Props.create(PrintActor.class),"PrintActor");
        ActorRef workerActor = system.actorOf(Props.create(WorkerActor.class),"WorkerActor");

        Future<Object> future = Patterns.ask(workerActor,5,1000);
        int result = (int)Await.result(future, Duration.create(3, TimeUnit.SECONDS));

        System.out.println("result:"+result);
        Future<Object> fut = Patterns.ask(workerActor,8,1000);
        Patterns.pipe(fut,system.dispatcher()).to(printActor);

        workerActor.tell(PoisonPill.getInstance(),ActorRef.noSender());
    }
}

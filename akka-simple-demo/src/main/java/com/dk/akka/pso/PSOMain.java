package com.dk.akka.pso;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 17:16
 **/
public class PSOMain {
    public static final int BIRD_COUNT = 100000;
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("psoSystem", ConfigFactory.load("sampleHello.conf"));
        ActorRef actorRef = system.actorOf(Props.create(MasterBird.class),"masterbird");
        System.out.println(actorRef.path());
        for (int i = 0; i < BIRD_COUNT; i++) {
            ActorRef brid =system.actorOf(Props.create(Bird.class),"bird_"+i);
//            System.out.println(brid.path());
        }
    }
}

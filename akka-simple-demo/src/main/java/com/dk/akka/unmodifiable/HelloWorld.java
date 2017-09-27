package com.dk.akka.unmodifiable;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.dk.common.json.FasterJsonTool;

import java.util.Arrays;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 17:44
 **/
public class HelloWorld extends UntypedActor {

    ActorRef greeter;

    @Override
    public void preStart() {
        // create the greeter actor
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter actor path：" + greeter.path());
        // tell it to perform the greeting
        greeter.tell(new Message(2, Arrays.asList("2", "dsf")), getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        try {
            System.out.println("HelloWorld收到的数据为：" + FasterJsonTool.writeValueAsString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

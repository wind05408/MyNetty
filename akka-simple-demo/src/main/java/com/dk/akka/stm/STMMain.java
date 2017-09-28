package com.dk.akka.stm;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.transactor.Coordinated;
import akka.util.Timeout;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;

import java.util.concurrent.TimeUnit;;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  软件事务内存（STM）：顾名思义，这是事务。与关系型数据库中的事务类似，具有ACID属性。
在分布式任务中，有可能会有和事务相关的处理，这里将举例说明AKKA中STM的用法。
 * @create 2017-09-28 16:41
 **/
public class STMMain {
    public static ActorRef companyActor = null;
    public static ActorRef employeeActor = null;

    public static void main(String [] args) throws Exception {
        ActorSystem system = ActorSystem.create("stm", ConfigFactory.load("akka.conf"));
        companyActor = system.actorOf(Props.create(CompanyActor.class), "CompanyActor");
        employeeActor = system.actorOf(Props.create(EmployeeActor.class), "EmployeeActor");

        Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

        for(int i = 0 ; i < 23; i ++){
            companyActor.tell(new Coordinated(i, timeout), ActorRef.noSender());

            Thread.sleep(200);

            int companyCount = (int) Await.result(Patterns.ask(companyActor, "getCount", timeout), timeout.duration());
            int employeeCount = (int) Await.result(Patterns.ask(employeeActor, "getCount", timeout), timeout.duration());

            System.out.println("companyCount = " + companyCount + ";employeeCount = " + employeeCount);
            System.out.println("-----------------------");
        }

    }
}

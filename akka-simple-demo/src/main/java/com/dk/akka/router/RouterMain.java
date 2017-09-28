package com.dk.akka.router;

import akka.actor.*;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.dk.akka.box.InboxMain;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:  通常在分布式任务调度系统中会有这样的需求：一组actor提供相同的服务，我们在调用任务的时候只需要选择其中一个actor进行处理即可。
其实这就是一个负载均衡或者说路由策略，akka作为一个高性能支持并发的actor模型，可以用来作为任务调度集群使用，当然负载均衡就是其本职工作了，akka提供了Router来进行消息的调度。
 * @create 2017-09-28 14:14
 **/
public class RouterMain extends UntypedActor {

    public Router router;
    {
        ArrayList<Routee> routees = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            //借用上面的 inboxActor
            ActorRef worker = getContext().actorOf(Props.create(InboxMain.class), "worker_" + i);
            getContext().watch(worker);//监听
            routees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }
    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof InboxMain.Msg){
            router.route(o, getSender());//进行路由转发
        }else if(o instanceof Terminated){
            router = router.removeRoutee(((Terminated)o).actor());//发生中断，将该actor删除。当然这里可以参考之前的actor重启策略，进行优化，为了简单，这里仅进行删除处理
            System.out.println(((Terminated)o).actor().path() + " 该actor已经删除。router.size=" + router.routees().size());

            if(router.routees().size() == 0){//没有可用actor了
                System.out.print("没有可用actor了，系统关闭。");
                flag.compareAndSet(true, false);
                getContext().system().shutdown();
            }
        }else {
            unhandled(o);
        }

    }

    public  static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("sampleHello.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterMain.class), "RouterMain");

        int i = 1;
        while(flag.get()){

            routerTest.tell(InboxMain.Msg.WORKING, ActorRef.noSender());
            if(i % 10 == 0) routerTest.tell(InboxMain.Msg.CLOSE, ActorRef.noSender());
            Thread.sleep(500);
            i ++;
        }
    }
}

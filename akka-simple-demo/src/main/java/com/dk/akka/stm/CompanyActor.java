package com.dk.akka.stm;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-28 16:41
 **/
public class CompanyActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private Ref.View<Integer> count = STM.newRef(100);//定义账户余额

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Coordinated){
            Coordinated coordinated = (Coordinated) o;
            int downCount = (int) coordinated.getMessage();//传递过来的参数，减多少。
            STMMain.employeeActor.tell(coordinated.coordinate(downCount), getSelf());//通知employeeActor增加费用

            try {//注意这里异常要及时处理，否则异常会一直扩散，导致回退到系统刚启动时的初始状态！
                coordinated.atomic(() -> {
                    if(count.get() < downCount) throw new RuntimeException("余额不足！");
                    STM.increment(count, -downCount);//减余额
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if("getCount".equals(o)){
            getSender().tell(count.get(), getSelf());
        }else{
            unhandled(o);
        }
    }

}

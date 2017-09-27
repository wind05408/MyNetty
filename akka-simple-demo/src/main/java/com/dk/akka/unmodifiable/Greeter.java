package com.dk.akka.unmodifiable;

import akka.actor.UntypedActor;
import com.dk.common.json.FasterJsonTool;
import org.json.simple.JSONObject;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 17:40
 **/
public class Greeter extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        try {
            System.out.println("Greeter收到的数据为：" + FasterJsonTool.writeValueAsString(msg));
            getSender().tell("Greeter工作完成。", getSelf());//给发送者发送信息.
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

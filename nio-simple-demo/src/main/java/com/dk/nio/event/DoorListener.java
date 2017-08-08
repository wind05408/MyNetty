package com.dk.nio.event;

import java.util.EventListener;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-08 15:09
 **/
public interface DoorListener extends EventListener {
    void doorEvent(DoorEvent event);
}

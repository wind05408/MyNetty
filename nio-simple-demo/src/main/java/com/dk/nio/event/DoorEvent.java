package com.dk.nio.event;

import java.util.EventObject;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-08 15:08
 **/
public class DoorEvent extends EventObject {

    private String doorState;

    public DoorEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public String getDoorState() {
        return doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }
}

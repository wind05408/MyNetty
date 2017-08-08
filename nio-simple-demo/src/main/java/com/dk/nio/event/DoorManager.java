package com.dk.nio.event;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-08-08 15:12
 **/
public class DoorManager {
    private Collection listeners;

    public void addDoorListener(DoorListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    public void removeDoorListener(DoorListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    protected void fireWorkspaceOpened() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "open");
        notifyListeners(event);
    }

    public void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "close");
        notifyListeners(event);
    }

    private void notifyListeners(DoorEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DoorListener listener = (DoorListener) iter.next();
            listener.doorEvent(event);
        }
    }
}

package com.dk.akka.pso;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 16:54
 **/
public final class GBestMsg {
    final PsoValue value;

    public GBestMsg(PsoValue v) {
        this.value = v;
    }

    public PsoValue getValue() {
        return value;
    }
}

package com.dk.akka.pso;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-10-09 16:59
 **/
public final class PBestMsg {
    final PsoValue value;

    public PBestMsg(PsoValue v) {
        this.value = v;
    }

    public PsoValue getValue() {
        return value;
    }

    @Override
    public String toString() {
      return value.toString();
    }
}

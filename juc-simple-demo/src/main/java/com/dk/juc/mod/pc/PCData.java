package com.dk.juc.mod.pc;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-06 19:18
 **/
public final class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}

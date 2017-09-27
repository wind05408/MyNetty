package com.dk.akka.unmodifiable;

import java.util.Collections;
import java.util.List;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-27 17:39
 **/
public final class Message {
    private final int age;
    private final List<String> list;

    public Message(int age, List<String> list) {
        this.age = age;
        this.list = Collections.unmodifiableList(list);
    }

    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return list;
    }
}

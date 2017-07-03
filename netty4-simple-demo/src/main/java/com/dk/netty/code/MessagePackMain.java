package com.dk.netty.code;

import com.google.common.collect.Lists;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * MessagePackMain
 *
 * @author dk
 * @date 2017/7/3 15:34
 */
public class MessagePackMain {
    public static void main(String[] args) throws IOException {
        //使用了guava
        List<String> src = Lists.newArrayList("msgpack", "kumofs", "viver");
        MessagePack msgpack = new MessagePack();
        //序列化
        byte[] raw = msgpack.write(src);
        //反序列化
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1);

    }
}

package com.dk.juc.concurrent;

/**
 * Created with IntelliJ IDEA
 * SyncMethod
 *
 * @author dk
 * @date 2017/7/20 17:10
 */
public class SyncMethod {
    public int i;

    public synchronized void syncTask(){//ACC_SYNCHRONIZED
        i++;
    }
}

//D:\dev\java1.7\bin\javap.exe -verbose com.dk.juc.concurrent.SyncMethod
//        Classfile /D:/dev/code/MyNetty/juc-simple-demo/target/classes/com/dk/juc/concurrent/SyncMethod.class
//Last modified 2017-7-20; size 407 bytes
//        MD5 checksum 41db63f5337cc794f565b49cfdcec463
//        Compiled from "SyncMethod.java"
//public class com.dk.juc.concurrent.SyncMethod
//        SourceFile: "SyncMethod.java"
//        minor version: 0
//        major version: 51
//        flags: ACC_PUBLIC, ACC_SUPER
//        Constant pool:
//        #1 = Methodref          #4.#17         //  java/lang/Object."<init>":()V
//        #2 = Fieldref           #3.#18         //  com/dk/juc/concurrent/SyncMethod.i:I
//        #3 = Class              #19            //  com/dk/juc/concurrent/SyncMethod
//        #4 = Class              #20            //  java/lang/Object
//        #5 = Utf8               i
//        #6 = Utf8               I
//        #7 = Utf8               <init>
//   #8 = Utf8               ()V
//           #9 = Utf8               Code
//           #10 = Utf8               LineNumberTable
//           #11 = Utf8               LocalVariableTable
//           #12 = Utf8               this
//           #13 = Utf8               Lcom/dk/juc/concurrent/SyncMethod;
//           #14 = Utf8               syncTask
//           #15 = Utf8               SourceFile
//           #16 = Utf8               SyncMethod.java
//           #17 = NameAndType        #7:#8          //  "<init>":()V
//           #18 = NameAndType        #5:#6          //  i:I
//           #19 = Utf8               com/dk/juc/concurrent/SyncMethod
//           #20 = Utf8               java/lang/Object
//           {
//public int i;
//        flags: ACC_PUBLIC
//
//public com.dk.juc.concurrent.SyncMethod();
//        flags: ACC_PUBLIC
//        Code:
//        stack=1, locals=1, args_size=1
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//        LineNumberTable:
//        line 10: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lcom/dk/juc/concurrent/SyncMethod;
//
//public synchronized void syncTask();
//        flags: ACC_PUBLIC, ACC_SYNCHRONIZED
//        Code:
//        stack=3, locals=1, args_size=1
//        0: aload_0
//        1: dup
//        2: getfield      #2                  // Field i:I
//        5: iconst_1
//        6: iadd
//        7: putfield      #2                  // Field i:I
//        10: return
//        LineNumberTable:
//        line 14: 0
//        line 15: 10
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      11     0  this   Lcom/dk/juc/concurrent/SyncMethod;
//        }
//
//        Process finished with exit code 0


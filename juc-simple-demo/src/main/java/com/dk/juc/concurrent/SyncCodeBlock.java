package com.dk.juc.concurrent;

/**
 * Created with IntelliJ IDEA
 * SyncCodeBlock
 *
 * @author dk
 * @date 2017/7/20 17:01
 */
public class SyncCodeBlock {


    public int i;

    public void syncTask(){
        //同步代码库
        synchronized (this){
            i++;
        }
    }

}

//D:\dev\java1.7\bin\javap.exe -verbose com.dk.juc.concurrent.SyncCodeBlock
//        Classfile /D:/dev/code/MyNetty/juc-simple-demo/target/classes/com/dk/juc/concurrent/SyncCodeBlock.class
//Last modified 2017-7-20; size 528 bytes
//        MD5 checksum 76607419b9cadcfa12ed67d362729731
//        Compiled from "SyncCodeBlock.java"
//public class com.dk.juc.concurrent.SyncCodeBlock
//        SourceFile: "SyncCodeBlock.java"
//        minor version: 0
//        major version: 51
//        flags: ACC_PUBLIC, ACC_SUPER
//        Constant pool:
//        #1 = Methodref          #4.#21         //  java/lang/Object."<init>":()V
//        #2 = Fieldref           #3.#22         //  com/dk/juc/concurrent/SyncCodeBlock.i:I
//        #3 = Class              #23            //  com/dk/juc/concurrent/SyncCodeBlock
//        #4 = Class              #24            //  java/lang/Object
//        #5 = Utf8               i
//        #6 = Utf8               I
//        #7 = Utf8               <init>
//   #8 = Utf8               ()V
//           #9 = Utf8               Code
//           #10 = Utf8               LineNumberTable
//           #11 = Utf8               LocalVariableTable
//           #12 = Utf8               this
//           #13 = Utf8               Lcom/dk/juc/concurrent/SyncCodeBlock;
//           #14 = Utf8               syncTask
//           #15 = Utf8               StackMapTable
//           #16 = Class              #23            //  com/dk/juc/concurrent/SyncCodeBlock
//           #17 = Class              #24            //  java/lang/Object
//           #18 = Class              #25            //  java/lang/Throwable
//           #19 = Utf8               SourceFile
//           #20 = Utf8               SyncCodeBlock.java
//           #21 = NameAndType        #7:#8          //  "<init>":()V
//           #22 = NameAndType        #5:#6          //  i:I
//           #23 = Utf8               com/dk/juc/concurrent/SyncCodeBlock
//           #24 = Utf8               java/lang/Object
//           #25 = Utf8               java/lang/Throwable
//           {
//public int i;
//        flags: ACC_PUBLIC
//
//public com.dk.juc.concurrent.SyncCodeBlock();
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
//        0       5     0  this   Lcom/dk/juc/concurrent/SyncCodeBlock;
//
//public void syncTask();
//        flags: ACC_PUBLIC
//        Code:
//        stack=3, locals=3, args_size=1
//        0: aload_0
//        1: dup
//        2: astore_1
//        3: monitorenter
//        4: aload_0
//        5: dup
//        6: getfield      #2                  // Field i:I
//        9: iconst_1
//        10: iadd
//        11: putfield      #2                  // Field i:I
//        14: aload_1
//        15: monitorexit
//        16: goto          24
//        19: astore_2
//        20: aload_1
//        21: monitorexit
//        22: aload_2
//        23: athrow
//        24: return
//        Exception table:
//        from    to  target type
//        4    16    19   any
//        19    22    19   any
//        LineNumberTable:
//        line 17: 0
//        line 18: 4
//        line 19: 14
//        line 20: 24
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      25     0  this   Lcom/dk/juc/concurrent/SyncCodeBlock;
//        StackMapTable: number_of_entries = 2
//        frame_type = 255 /* full_frame */
//        offset_delta = 19
//        locals = [ class com/dk/juc/concurrent/SyncCodeBlock, class java/lang/Object ]
//        stack = [ class java/lang/Throwable ]
//        frame_type = 250 /* chop */
//        offset_delta = 4
//
//        }
//
//        Process finished with exit code 0


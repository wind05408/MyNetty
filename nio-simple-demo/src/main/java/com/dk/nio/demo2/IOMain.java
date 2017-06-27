package com.dk.nio.demo2;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA
 * IOMain
 *
 * @author dk
 * @date 2017/6/27 16:48
 */
public class IOMain {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("d://test.txt","rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,channel.size());
            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            byte[] b = new byte[1024];

            long len = file.length();
            long startTime = System.currentTimeMillis();
            //读取内存映射文件
            for(int i=0;i<file.length();i+=1024*10){
                if (len - i > 1024) {
                    buffer.get(b);
                } else {
                    buffer.get(new byte[(int)(len - i)]);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("使用内存映射方式读取文件总耗时： "+(endTime - startTime));


            //普通IO流方式
            long startTime1 = System.currentTimeMillis();
            while(channel.read(buffer1) > 0){
                buffer1.flip();
                buffer1.clear();
            }

            long endTime1 = System.currentTimeMillis();
            System.out.println("使用普通IO流方式读取文件总耗时： "+(endTime1 - startTime1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

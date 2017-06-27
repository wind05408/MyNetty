package com.dk.nio.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created with IntelliJ IDEA
 * NIOLock
 *
 * @author dk
 * @date 2017/6/27 19:16
 */
public class NIOLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(NIOLock.class);

    public static void main(String[] args) throws IOException {

        FileChannel fileChannel = new RandomAccessFile("d://12.txt","rw").getChannel();

        fileChannel.write(ByteBuffer.wrap("abcd".getBytes()));

        FileLock lock1 = fileChannel.lock(0,2,true);

        lock1.isShared();

        fileChannel.write(ByteBuffer.wrap("ef".getBytes()),2);

        lock1.channel();

        long position = lock1.position();

        long size = lock1.size();

        LOGGER.info("position={},size={}",position,size);

        lock1.overlaps(position,size);

        lock1.release();




    }
}

package com.dk.nio.demo1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA
 * NIOServer
 * Selector,ServerSocketChannel, ByteBuffer
 * 臭名昭著的epoll bug，它会导致Selector空轮询，最终导致CPU 100%
 *
 * Selector,Channle,Buffer
 * dev branch test
 * @author dk
 *
 * @date 2017/6/27 14:33
 */
public class NIOServer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //通道管理器
    private Selector selector;

    /**
     * 获取一个ServerSocker通道，并对通道做一些初始化的工作
     * @param port 绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        //获取一个ServerSocket通道 ServerSocketChannel.open()
        ServerSocketChannel channel = ServerSocketChannel.open();

        //设置channel的阻塞方式。非阻塞。
        channel.configureBlocking(false);

        //将channel绑定端口
        channel.socket().bind(new InetSocketAddress(port));

        //获取一个通道管理器
        this.selector =Selector.open();

        //通道管理器与通道绑定register，并为该通道注册SelectionKey.OP_ACCEPT事件
        //注册该事件后，当该事件到达时，selector.select()会返回，
        //如果该事件没到达selector.select()会一直阻塞。
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    public void listen() throws IOException {
        logger.info("================NIOServer服务端启动成功！====================");

        // 轮询访问selector
        while (true){

            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();

            //获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator iter =  this.selector.selectedKeys().iterator();

            while (iter.hasNext()){

                SelectionKey key = (SelectionKey) iter.next();
                // 删除已选的key,以防重复处理
                iter.remove();

                // 客户端请求连接事件
                if(key.isAcceptable()){
                    accept(key);
                }else if(key.isReadable()){
                    read(key);
                }
            }

        }
    }

    public void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();

        // 获得和客户端连接的通道
        SocketChannel channel = server.accept();

        // 设置成非阻塞
        channel.configureBlocking(false);

        //向客户端发送消息
        channel.write(ByteBuffer.wrap(new String("=====send msg to client").getBytes()));

        //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权
        channel.register(this.selector,SelectionKey.OP_READ);
    }

    /**
     * 处理读取客户端发来的信息的事件
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {

        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();

        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(80);
        channel.read(buffer);

        byte[] data = buffer.array();

        String msg = new String(data).trim();

        logger.info("=================服务端收到信息："+msg);

        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());

        // 将消息回送给客户端
        channel.write(outBuffer);

    }


    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(7070);
        server.listen();

    }

}

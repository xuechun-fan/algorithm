package com.fxc.os.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * socket服务器类
 *
 * @author FXC
 */
public class DateServer {
    public static void main(String[] args) {
        try {
            // 创建socket套接字，并绑定6013端口
            ServerSocket socket = new ServerSocket(6013);
            while (true) {
                System.out.println("等待链接...");
                // 服务端套接字调用accept()方法，阻塞监听6013端口，直到客户端请求连接并成功建立链接，则返回客户端的socket套接字
                Socket client = socket.accept();
                // 使用客户端socket创建IO对象
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                // 向客户端发送数据
                pout.println(new Date().toString());
                // 关闭客户端链接的套接字
                client.close();
                System.out.println("发送数据成功，关闭当前连接");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
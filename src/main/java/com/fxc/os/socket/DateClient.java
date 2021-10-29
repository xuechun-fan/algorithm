package com.fxc.os.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端Socket套接字类
 *
 * @author FXC
 */
public class DateClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6013);
            InputStream in = socket.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            // 从套接字中读取数据
            String line;
            while ((line = bin.readLine()) != null) {
                System.out.println(line);
            }
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
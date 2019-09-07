package com.example.myapplication.com.example.myapplication.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ChatManager {

    private Socket socket;
    int PORT;
    String IP;
    BufferedReader reader;
    PrintWriter writer;

    private ChatManager() {
    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getCM() {
        return instance;
    }

    //链接服务器
    public void connect(String ip,int port) {
        this.IP = ip;
        this.PORT = port;
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("链接IP："+IP+"链接port:"+PORT);
                    socket = new Socket(IP, PORT);

                    //获取socket的输入流和输出流，客户端的输入流和服务端的输出流项链
                    writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("获取输入流："+reader);
                    System.out.println("获取输出流："+writer);
                    String line;
                    while ((line = reader.readLine())!=null){
                        System.out.println(line);
                    }
                    writer.close();
                    reader.close();
                    writer =null;
                    reader = null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("链接失败");
                }
            }
        }.start();
    }

    //发送数据
    public void send(String out){
//        System.out.println("writer是否为空："+writer!=null);
        if(writer != null){
            writer.write(out+"\n");
            writer.flush();
//            System.out.println("写到了这里");
        }

    }



}

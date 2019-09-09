package com.example.myapplication.com.example.myapplication.activity;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.DataApplication;

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
    DataApplication dataApplication;
    String message;
    private ChatManager() {
        dataApplication = DataApplication.getDataApplication();
    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getCM() {
        return instance;
    }

    //链接服务器
    public void connect(Context context,String ip, int port) {
        this.IP = ip;
        this.PORT = port;
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("链接IP：" + IP + "链接port:" + PORT);
                    socket = new Socket(IP, PORT);
                    //获取socket的输入流和输出流，客户端的输入流和服务端的输出流项链
                    writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("获取输入流：" + reader);
                    System.out.println("获取输出流：" + writer);
                    message = IP+":"+PORT+"链接成功";
//                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                    dataApplication.setStaConnect(1);           //链接成功
                } catch (IOException ex) {
                    dataApplication.setStaConnect(0);           //链接失败
                    message = IP+":"+PORT+"链接失败";
//                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                    System.out.println("链接IP：" + IP + "链接port:" + PORT+"，链接失败");
                    try {
                        sleep(5000);
                        connect(context,ip,port);           //等待5s后自动重新链接
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("等待出现了问题" );
                    }
                }
            }
        }.start();
    }

    //发送数据
    public void send(String out) {
        if (writer != null) {
            writer.write(out + "\n");
            writer.flush();
        }
    }
    public BufferedReader getServerMeg(){
        return this.reader;
    }

    public void getMessaget() {
        String line;
        while (true) {
            try {
                if (((line = reader.readLine()) != null)) {
                    System.out.println(line);
                    dataApplication.setValue("retServer", line);//把服务器的返回结果保存到dataApplication.retServer里面；
                }
                break;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer = null;
                reader = null;
            }

        }

    }

}

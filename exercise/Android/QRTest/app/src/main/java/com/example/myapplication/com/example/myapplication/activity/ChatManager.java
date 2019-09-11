package com.example.myapplication.com.example.myapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
    private Object File;

    private ChatManager() {
        dataApplication = DataApplication.getDataApplication();
    }
    private static final ChatManager instance = new ChatManager();
    public static ChatManager getCM() {
        return instance;
    }
    public int clock = 0;
    private Handler subHandler;
    //链接服务器
    public void connect(Context context, String ip, int port) {
        this.IP = ip;
        this.PORT = port;
        Toast.makeText(context,"连接到了connect界面,连接信息："+dataApplication.getStaConnect(),Toast.LENGTH_SHORT).show();
        if(dataApplication.getStaConnect() == 0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    try {
                        dataApplication.setStaConnect(2);
                        System.out.println("正在连接IP：" + IP + ":" + PORT);
                        socket = new Socket(IP, PORT);
                        //获取socket的输入流和输出流，客户端的输入流和服务端的输出流项链
                        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println("获取输入流：" + reader);
                        System.out.println("获取输出流：" + writer);
                        message = IP + ":" + PORT + "链接成功";

                        subHandler = new Handler() {
                            public void handleMessage(Message msg) {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        };
//                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        dataApplication.setStaConnect(1);           //链接成功
                    } catch (IOException ex) {
                        dataApplication.setStaConnect(0);           //链接失败
                        message = IP + ":" + PORT + "链接失败";
                        subHandler = new Handler() {
                            public void handleMessage(Message msg) {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        };
                        if(clock < 5){
                            clock++;
                            SystemClock.sleep(5000);
                            System.out.println("链接IP：" + IP + "链接port:" + PORT + "，链接失败，等待5s后重新连接");
                            connect(context, IP, PORT);
                        }else{
                            System.out.println(" 连接失败，请检查");
                       }

//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//                    }
                    }
                    Looper.loop();
                }
            }).start();
        }
    }

    //发送数据
    public void send(char[] out) {
        if (writer != null) {
            writer.write(out);
//            writer.write(out + "\n");
            writer.flush();
            //开头时#03#和#04#时
//            if (out[2] == 0233 || out == "#04#") {
//                dataApplication.protecte = !dataApplication.protecte;
//            }
        }
    }

    public BufferedReader getServerMeg() {
        return this.reader;
    }
//    public void getMessaget() {
//        String line;
//        while (true) {
//            try {
//                if (((line = reader.readLine()) != null)) {
//                    System.out.println(line);
//                    dataApplication.setValue("retServer", line);//把服务器的返回结果保存到dataApplication.retServer里面；
//                    System.out.println("收到的信息："+dataApplication.getServerMeg());
//                }
//                break;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                writer.close();
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                writer = null;
//                reader = null;
//            }
//
//        }
//
//    }

    public void closeServer(){


        try {
            writer.close();
            socket.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("关闭出现了异常");
        }

    }

}




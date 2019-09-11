package com.uovision.mysocketservice.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uovision.mysocketservice.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Socket socket;
    private Thread socketService;
    private ServerSocket serverSocket;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        socketService = new Thread(){
            @Override
            public void run() {
                startSocketServer();
            }
        };
        socketService.start();
        return root;
    }
    InputStream iputStream;
    OutputStream outputStream;
    private void startSocketServer() {
        try {
            serverSocket = new ServerSocket(1989);
            // 调用ServerSocket的accept()方法，接受客户端所发送的请求，
            // 如果客户端没有发送数据，那么该线程就停滞不继续
            socket = serverSocket.accept();
            iputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            getMessage(iputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessage(final InputStream inputStream){
        System.out.println("连接成功");
        new Thread() {
            public void run() {
                try {
                   int i =  inputStream.read();
                   System.out.println(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
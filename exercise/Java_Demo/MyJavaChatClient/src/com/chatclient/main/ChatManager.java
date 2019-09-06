package com.chatclient.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.zip.InflaterInputStream;

import com.chatclient.view.MainWindow;

public class ChatManager {
	Socket socket;
	int port = 22567;
	String IP;
	BufferedReader reader;
	PrintWriter writer;
	
	private ChatManager() {}
	private static final ChatManager instance = new ChatManager();
	public static ChatManager getCM() {
		return instance;
	}
	
	//链接服务器；
	public void connect(String ip) {
		this.IP = ip;
		new Thread() {
			public void run() {
				try {
					socket = new Socket(IP, port);
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String line;
					while((line = reader.readLine())!=null) {
						window.appendText("收到："+line);
					}
					writer.close();
					reader.close();
					writer = null;
					reader = null;
				} catch (Exception e) {
					window.appendText("未链接该ip");
				} 
			}
		}.start();
	}

	//发送数据
	public void send(String out) {
		 if(writer != null) {
			 //加了\n能强制把out内容输出
			 writer.write(out+"\n");
			 writer.flush();
		 }else {
			 window.appendText("当前链接已经中断");
		 }
	}

	MainWindow window;
	//和文本框的绑定
	public void setWindow(MainWindow window) {
		this.window = window;
		window.appendText("文本框已经和ChatManager绑定");
	}
	
	
}

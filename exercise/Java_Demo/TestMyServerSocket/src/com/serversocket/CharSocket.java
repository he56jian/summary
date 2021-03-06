package com.serversocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

//聊天室
public class CharSocket extends Thread {
	Socket socket;
	public CharSocket(Socket socket) {
		this.socket = socket;
	}
	//服务器发送到客户端数据
	public void send(String out){
		//要创建一个输出流，才能向外输出数据
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (Exception e) {
			System.out.print("报错点1");
		} 
	}
	
	@Override
	public void run()   {
//		执行后台功能
		try {
			//创建一个输入流，获取数据
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String line = null;
			//如果获取的数据不为空
			while((line = br.readLine()) != null){
				ChatManager.getChatManager().publish(this, line);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.print("断开链接");
		}
		
			
		
	}
	
}

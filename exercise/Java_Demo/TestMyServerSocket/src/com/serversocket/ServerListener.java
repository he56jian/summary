package com.serversocket;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	
	@Override
	public void run(){
		int port = 22567;
		try{
			ServerSocket serversocket = new ServerSocket(port);
			//循环监听客户端连接
			while(true){
				Socket socket = serversocket.accept();		//每次有客户端连接，都会生成一个socket对象
				JOptionPane.showMessageDialog(null,"有客户端连接");
				//每个socket又对应一个新的客户端，所以要重新开一个线程，创建用于socket通信的线程
				CharSocket cs = new CharSocket(socket);
				 cs.start();
				 ChatManager.getChatManager().add(cs);//把客户端，添加到客户端管理器中
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

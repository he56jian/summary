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
			//ѭ�������ͻ�������
			while(true){
				Socket socket = serversocket.accept();		//ÿ���пͻ������ӣ���������һ��socket����
				JOptionPane.showMessageDialog(null,"�пͻ�������");
				//ÿ��socket�ֶ�Ӧһ���µĿͻ��ˣ�����Ҫ���¿�һ���̣߳���������socketͨ�ŵ��߳�
				CharSocket cs = new CharSocket(socket);
				 cs.start();
				 ChatManager.getChatManager().add(cs);//�ѿͻ��ˣ���ӵ��ͻ��˹�������
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

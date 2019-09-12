package com.serversocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

//������
public class CharSocket extends Thread {
	Socket socket;
	public CharSocket(Socket socket) {
		this.socket = socket;
	}
	//���������͵��ͻ�������
	public void send(String out){
		//Ҫ����һ������������������������
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (Exception e) {
			System.out.print("������1");
		} 
	}
	
	@Override
	public void run()   {
//		ִ�к�̨����
		try {
			//����һ������������ȡ����
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String line = null;
			//�����ȡ�����ݲ�Ϊ��
			while((line = br.readLine()) != null){
				ChatManager.getChatManager().publish(this, line);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.print("�Ͽ�����");
		}
		
			
		
	}
	
}
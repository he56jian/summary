package com.serversocket;

import java.util.Vector;

public class ChatManager {
	//һ�������������ֻ����һ�������manager��Ҫ����������
	
	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager(){
		return cm;
	}
	Vector<CharSocket> vector = new Vector<>();
	
	public void add(CharSocket cs){
		vector.add(cs);
	}
	
	//����ĳ���̣߳�����ͨ���÷����������ͻ��˷�����Ϣ
	public void publish(CharSocket cs,String out){
		for (int i = 0; i < vector.size(); i++) {
			CharSocket csChatSocket = vector.get(i);
			//�����ǰ�������Һͻ�ȡ�������Ҳ�һ�£�����
			if(!cs.equals(csChatSocket)){
				csChatSocket.send(out);
			}
			
		}
	}
	
	
}
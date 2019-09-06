package com.chatclient.main;

import java.awt.EventQueue;

import com.chatclient.view.MainWindow;

public class StartClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					
					frame.setVisible(true); 
					ChatManager.getCM().setWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}

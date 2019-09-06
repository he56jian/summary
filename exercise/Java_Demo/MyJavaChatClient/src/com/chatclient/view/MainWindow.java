package com.chatclient.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chatclient.main.ChatManager;

import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea txt;
	private JTextField ip;
	private JTextField send;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setAlwaysOnTop(true);//保持窗体显示在前面
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txt = new JTextArea();
		txt.setText("read...");
		
		ip = new JTextField();
		ip.setText("127.0.0.1");
		ip.setColumns(10);
		
		JButton btnNewButton =  new JButton("链接到服务器");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//点击链接到服务器
//				ip.getText();//获取要链接的ip地址；
				ChatManager.getCM().connect(ip.getText());
			}
		});
		
		send = new JTextField();
		send.setText("你好");
		send.setColumns(10);
		
		button = new JButton("\u53D1\u9001");
		button.addMouseListener(new MouseAdapter() {
			//点击发送
			public void mouseClicked(MouseEvent e) {
				ChatManager.getCM().send(send.getText());
				appendText("我说："+send.getText());
				send.setText("");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ip, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addComponent(send, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txt, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(send, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void appendText(String in) {
		txt.append("\n"+ in);
	}
}

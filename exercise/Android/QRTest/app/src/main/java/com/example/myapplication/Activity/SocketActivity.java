package com.example.myapplication.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.23.1";
    private static final int PORT = 9999;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_socket);
//        mEditText = (EditText) findViewById(R.id.editText);
//        mTextView = (TextView) findViewById(R.id.textView);
        mExecutorService = Executors.newCachedThreadPool();
    }

    public void connect(View view) {
        mExecutorService.execute(new connectService());  //在一个新的线程中请求 Socket 连接
    }

    public void send(View view) {
        String sendMsg = mEditText.getText().toString();
        mExecutorService.execute(new sendService(sendMsg));
    }

    public void disconnect(View view) {
        mExecutorService.execute(new sendService("0"));
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {//可以考虑在此处添加一个while循环，结合下面的catch语句，实现Socket对象获取失败后的超时重连，直到成功建立Socket连接
            try {
                Socket socket = new Socket(HOST, PORT);      //步骤一
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));   //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {                                      //步骤三
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(receiveMsg + "\n\n" + mTextView.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}
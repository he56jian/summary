package com.example.myapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.BaseMethods.Utils;
import com.example.myapplication.ConnectSocket.ChatManager;
import com.example.myapplication.Database.DataApplication;
import com.example.myapplication.R;

public class ConnectSettingActivity extends Activity implements View.OnClickListener {
    EditText edit_port, edit_ip,edit_message;
    DataApplication dataApplication;
    String IP;

    Button button_tcp_connec, button_send, button_save,button_test;
    int PORT =5001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);

        edit_ip = findViewById(R.id.edit_ip);
        edit_port = findViewById(R.id.edit_port);
        edit_ip = findViewById(R.id.edit_ip);
        dataApplication = DataApplication.getDataApplication();
        Utils utils = new Utils(this);
        button_tcp_connec = findViewById(R.id.button_tcp_connec);
        button_save = findViewById(R.id.button_save);
        button_send = findViewById(R.id.button_send);
        button_test = findViewById(R.id.button_test);

        button_tcp_connec.setOnClickListener(this);
        button_save.setOnClickListener(this);
        button_send.setOnClickListener(this);
        button_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_tcp_connec:
                if (dataApplication.getStaConnect() == 0) {
                    IP =  edit_ip.getText().toString();
                    if (IP.equals("")) {
                        Toast.makeText(ConnectSettingActivity.this, "请输入IP", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConnectSettingActivity.this, "开始连接", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ConnectSettingActivity.this, "连接IP："+IP, Toast.LENGTH_SHORT).show();
                        ChatManager.getCM().connect(ConnectSettingActivity.this, IP, PORT);
                    }
                }else if(dataApplication.getStaConnect() == 1){
                    Toast.makeText(ConnectSettingActivity.this, "已经连接上了"+IP, Toast.LENGTH_SHORT).show();
                }else if(dataApplication.getStaConnect() == 2){
                    Toast.makeText(ConnectSettingActivity.this, "正在连接。。。", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_save:
                if(edit_ip.getText().toString().equals("")){
                    IP = "192.168.0.1";
                }else{
                    IP = edit_ip.getText().toString();
                }

                if(edit_port.getText().toString().equals("")){
                    PORT = 5001;
                }else{
                    PORT = Integer.parseInt(edit_port.getText().toString());
                }
                dataApplication.setIP(IP);
                dataApplication.setPORT(PORT);
                break;
            case R.id.button_send:
                if(!edit_message.getText().toString().equals("")){
//                    ChatManager.getCM().send();
                }else{
                    Toast.makeText(this,"未输入内容",Toast.LENGTH_SHORT);
                }
               break;
            case R.id.button_test:
                Utils utils = new Utils(this);
                dataApplication.getIP();
                utils.isAvailableByPing( dataApplication.getIP());
                break;
        }
    }
}

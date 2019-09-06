package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetActivity extends Activity {

    private List<String> list_sendmode = new ArrayList<String>();
    private List<String> list_control = new ArrayList<String>();
    private Spinner spinner_sendmode, spinner_control;
    private DataApplication dataApplication;
    private String sendMode,remoteControl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net);
        dataApplication = new DataApplication(this).getDataApplication();
        getView();
    }

    //获取元素
    private void getView() {
        sendMode = dataApplication.getValue("sendMode");
        remoteControl = dataApplication.getValue("remoteControl");

        spinner_sendmode = findViewById(R.id.spinner_sendmode);
        spinner_control = findViewById(R.id.spinner_control);

        spinner_sendmode.setPrompt(sendMode);
        init();
    }
    //初始化参数
    private void init() {
        Utils utils = new Utils(this);
        if (list_sendmode.isEmpty()) {
            list_sendmode.add("Unlimited");
            for(int i = 1;i<99;i++){
                String msg = i+" file/Day";
                list_sendmode.add(msg);
            }
        }

        //photo size选择器选择监听
        utils.listSelect(list_sendmode, spinner_sendmode, "sendMode");
        if (list_control.isEmpty()) {
            list_control.add("Realtime");
            list_control.add("Delay 0.5H");
            list_control.add("Delay 1H");
            list_control.add("Delay 2H");
            list_control.add("Delay 3H");
            list_control.add("Delay 4H");
            list_control.add("Delay 6H");
            list_control.add("Delay 12H");
            list_control.add("Delay 24H");
        }
        //photo size选择器选择监听
        utils.listSelect(list_control, spinner_control, "remoteControl");
    }
}

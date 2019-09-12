package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    private Spinner spinner_control;
    private EditText edit_sendmode;
    private DataApplication dataApplication;
    private String sendMode, remoteControl;
    private Button button_save;
    private Utils utils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net);
        dataApplication = new DataApplication(this).getDataApplication();
        init();
        getView();
    }

    //获取元素
    private void getView() {
        sendMode = dataApplication.getValue("sendMode");
        remoteControl = dataApplication.getValue("remoteControl");

        edit_sendmode.setText(sendMode);
        utils.setSpinnerDefaultValue(spinner_control, remoteControl);

        System.out.println(spinner_control);

        button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMode = edit_sendmode.getText().toString();      //获取输入值
                dataApplication.setSendMode(sendMode);

//                Toast.makeText(NetActivity.this, sendMode, Toast.LENGTH_LONG).show();
//                System.out.println(dataApplication.getSendMode());
            }
        });
        edit_sendmode.setText(sendMode);

    }

    //初始化参数
    private void init() {
        utils = new Utils(this);
        edit_sendmode = findViewById(R.id.spinner_sendmode);
        spinner_control = findViewById(R.id.spinner_control);

        //photo size选择器选择监听
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

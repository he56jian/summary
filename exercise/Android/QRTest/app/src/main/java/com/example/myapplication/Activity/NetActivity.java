package com.example.myapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.Database.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.BaseMethods.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private List<String> list_sendmode = new ArrayList<String>();
    private List<String> list_control = new ArrayList<String>();
    private Spinner spinner_control;
    private EditText edit_sendmode;
    private DataApplication dataApplication;
    private String sendMode, remoteControl;
    private Button button_save;
    private Utils utils;
    private int remoteControlIndex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net);
        dataApplication = new DataApplication(this).getDataApplication();
        init();     //初始化
        getView();
    }

    //获取元素
    private void getView() {
        sendMode = dataApplication.getSendMode();
        edit_sendmode.setText(sendMode);

        button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMode = edit_sendmode.getText().toString();      //获取输入值
                dataApplication.setSendMode(sendMode);
                dataApplication.setRemoteControl(remoteControl);
                dataApplication.setRemoteControlIndex(remoteControlIndex);
            }
        });

    }

    //初始化参数
    private void init() {
        utils = new Utils(this);
        edit_sendmode = findViewById(R.id.spinner_sendmode);
        spinner_control = findViewById(R.id.spinner_control);
        remoteControlIndex = dataApplication.getRemoteControlIndex();

        spinner_control.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getRemoteControlList()));
        spinner_control.setOnItemSelectedListener(this);
        spinner_control.setSelection(remoteControlIndex);
        System.out.println("----------------"+remoteControlIndex);
//        //photo size选择器选择监听
//        if (list_control.isEmpty()) {
//            list_control.add("Realtime");
//            list_control.add("Delay 0.5H");
//            list_control.add("Delay 1H");
//            list_control.add("Delay 2H");
//            list_control.add("Delay 3H");
//            list_control.add("Delay 4H");
//            list_control.add("Delay 6H");
//            list_control.add("Delay 12H");
//            list_control.add("Delay 24H");
//        }
//        //photo size选择器选择监听
//        utils.listSelect(list_control, spinner_control, "remoteControl");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String value = adapterView.getItemAtPosition(i).toString();
        switch (adapterView.getId()){
            case R.id.spinner_control:
                remoteControl = value;
                remoteControlIndex = i;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

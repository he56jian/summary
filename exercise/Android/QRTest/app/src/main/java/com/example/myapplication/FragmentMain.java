package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.com.example.myapplication.activity.ChatManager;

public class FragmentMain extends FragmentActivity implements View.OnClickListener{

    Button btn_parcam,btn_connect,btn_other;
    FrameLayout show_des;
    ParamFragment paramFragment;
    String IP = "192.168.0.1";
    int PORT = 5001;
    char[] chars=  new char[]{'#','0','1','#'};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ParamFragment paramFragment = new ParamFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.show_des, paramFragment).commit();
        DataApplication dataApplication = DataApplication.getDataApplication();
        ChatManager.getCM().connect(this, IP, PORT);
        System.out.println("运行了FragmentMain界面");
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (dataApplication.sta_connect == 1) {
                        ChatManager.getCM().send(chars);               //一开始就发送#01#
                        break;
                    }
                }
            }
        }.start();

        show_des = findViewById(R.id.show_des);
        btn_parcam = findViewById(R.id.btn_parcam);
        btn_connect = findViewById(R.id.btn_connect);
        btn_other = findViewById(R.id.btn_other);
        btn_parcam.setOnClickListener(this);
        btn_connect.setOnClickListener(this);
        btn_other.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_parcam:
                ParamFragment paramFragment = new ParamFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.show_des, paramFragment).commit();
                break;
            case R.id.btn_connect:
                ConnectFragment con = new ConnectFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.show_des, con).commit();
                break;
            case R.id.btn_other:
                break;

        }
    }
}

package com.example.myapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ConnectSocket.ChatManager;
import com.example.myapplication.Database.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.BaseMethods.Utils;

import java.io.BufferedReader;

import static java.lang.Thread.sleep;

public class ConnectFragment extends Fragment implements View.OnClickListener {

    Context context;
    View view;
    private TextView textview;
    LinearLayout showMessage;
    DataApplication dataApplication;
    private BufferedReader reader;
    private String line;
    int sta_connect;
    Button button_send;
    EditText editText;
    String eqcode;
    Utils utils;
    Thread thread;
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        char[] arr = "#03#".toCharArray();
        ChatManager.getCM().sendCharToStream(arr,true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载上面的布局文件
        view = inflater.inflate(R.layout.connect_client, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    //添加View
    public void addView(LinearLayout linearLayout,String value) {
        TextView child = new TextView(context);
        child.setTextSize(20);
        child.setTextColor(getResources().getColor(R.color.colorAccent));
        // 获取当前的时间并转换为时间戳格式, 并设置给TextView
        String currentTime = utils.dateToStamp(System.currentTimeMillis());
        child.setText(currentTime+":"+value);
        // 调用一个参数的addView方法
        linearLayout.addView(child);
    }
    String sendMessage;
    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.button_send:
//                sendMessage = editText.getText().toString();
//                ChatManager.getCM().send(sendMessage);
//                addView(showMessage,"发送内容:"+sendMessage);
//                break;
//        }
    }
}

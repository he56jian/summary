package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.com.example.myapplication.activity.CameraActivity;
import com.example.myapplication.com.example.myapplication.activity.ChatManager;
import com.example.myapplication.com.example.myapplication.activity.NetActivity;
import com.example.myapplication.com.example.myapplication.activity.SimInfoActivity;
import com.example.myapplication.com.example.myapplication.activity.SysActivity;
import com.example.myapplication.com.example.myapplication.activity.TriggerModeActivity;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ParamFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private DataApplication dataApplication;
    private View view;
    private Intent intent;
    private String cameraMode, photoSize, photoBurst, sendingOption, shutterSpeed, flashPower, videoSize, videoLength, triggerPir, triggerSen, triggerTimelapse,  sendMode, remoteControl, passWord, rename;
    private int sta_name, sta_password, sta_overWrite;

    private TextView textView_cameraMode, textView_cameraFlash, textView_triggerPir, textView_timelapse, textView_worktime1, textView_worktime2, textView_worktime3, textView_worktime4,textview_container;
    private ImageView imageView;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    String IP;
    Utils utils;
    int PORT;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载上面的布局文件
        view = inflater.inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        utils = new Utils(context);
        dataApplication = new DataApplication(context).getDataApplication();
        dataApplication.defaultSetting();
        init();
    }

    private void init() {
//        getCamParam();      //获取相机参数
        getEle();           //获取元素
//        showCameParam();//显示相机数据
        view.findViewById(R.id.button_camera).setOnClickListener(this);              //点击camer mode
        view.findViewById(R.id.button_trigger).setOnClickListener(this);               //点击trigger
//        view.findViewById(R.id.button_worktime).setOnClickListener(this);               //点击work time
        view.findViewById(R.id.button_net).setOnClickListener(this);            //点击net
        view.findViewById(R.id.button_sys).setOnClickListener(this);                  //点击rename
//        view.findViewById(R.id.button_zxing).setOnClickListener(this);           //生成二维码
        view.findViewById(R.id.button_send).setOnClickListener(this);           //发送参数
        view.findViewById(R.id.button_setting).setOnClickListener(this);           //发送参数
        view.findViewById(R.id.button_connectServer).setOnClickListener(this);           //发送参数
        view.findViewById(R.id.button_connectServer1).setOnClickListener(this);           //发送参数
        view.findViewById(R.id.button_connectServer2).setOnClickListener(this);           //发送参数
        view.findViewById(R.id.button_sim_info).setOnClickListener(this);           //发送参数
    }

    //获取元素
    private void getEle() {
        textView_cameraMode = view.findViewById(R.id.textview_camera_mode);
        textView_cameraFlash = view.findViewById(R.id.textview_camera_flash);
        textView_triggerPir = view.findViewById(R.id.textview_trigger_pir);
        textView_timelapse = view.findViewById(R.id.textview_trigger_timelapse);
        imageView = view.findViewById(R.id.imageView_zxing);                    //生成的二维码
    }

    //显示相机数据
    private void showCameParam() {
        String message;
        if (cameraMode == "photo") {
            message = cameraMode + " ( " + photoSize + " | " + photoBurst + " ) ";
            textView_cameraMode.setText(message);
            message = "Flash Power( " + flashPower + " )";
            textView_cameraFlash.setText(message);
        } else {
            message = cameraMode;
            textView_cameraMode.setText(message);
            message = " ( " + videoSize + " | " + videoLength + "  ) ";
            textView_cameraFlash.setText(message);
        }
        if (triggerSen == "off") {
            message = "off";
            textView_triggerPir.setText(message);
        } else {
            message = "PIR ( " + triggerSen + " | " + triggerPir + " )";
            textView_triggerPir.setText(message);
        }
        if (triggerTimelapse == "off") {
            textView_timelapse.setText(triggerTimelapse);
        } else {
            message = "TimeLapse ( " + triggerTimelapse + " )";
            textView_timelapse.setText(message);
        }
    }
    private char[] showMessage;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.button_zxing:                                     //生成二维码
//                intent = new Intent(context, ShowQRCodeActivity.class);
//                startActivityForResult(intent, 1);
//                break;
            case R.id.button_send:                    //发送信息
                toSend3();
//                toSend2();
//                new Thread(){
//                    @Override
//                    public void run() {
//                        toSend();
//                    }
//                }.start();
                break;
            case R.id.button_camera:                    //单击进入cameraMode界面
                intent = new Intent(context, CameraActivity.class);
                startActivityForResult(intent, 1);                //回执
                break;
            case R.id.button_trigger:                   //点击进入触发界面
                intent = new Intent(context, TriggerModeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_net:                   //点击进入设置net界面
                intent = new Intent(context, NetActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_sim_info:                  //点击进入sim卡设置页面
                intent = new Intent(context, SimInfoActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_sys:
                intent = new Intent(context, SysActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_setting:
                intent = new Intent(context,ConnectSetting.class);
                startActivityForResult(intent,1);
                break;

            case R.id.button_connectServer:
                IP = dataApplication.getIP();
                PORT = dataApplication.getPORT();
                Toast.makeText(context,"连接"+IP,Toast.LENGTH_SHORT).show();
                ChatManager.getCM().connect(context, IP, PORT);
                break;
            case R.id.button_connectServer1:
                IP = "192.168.0.1";
                PORT = 5001;
                Toast.makeText(context,"连接"+IP,Toast.LENGTH_SHORT).show();
                ChatManager.getCM().connect(context, IP, PORT);
                break;
            case R.id.button_connectServer2:
                IP = "192.168.1.194";
                PORT = 5001;
                Toast.makeText(context,"连接"+IP,Toast.LENGTH_SHORT).show();
                ChatManager.getCM().connect(context, IP, PORT);
                break;
//            case R.id.button_start:
//                new IntentIntegrator(this)
//                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
//                        //.setPrompt("请对准二维码")// 设置提示语
//                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
//                        .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
//                        .initiateScan();// 初始化扫码
//                break;
//            case R.id.button_native:
//                new IntentIntegrator(this)
//                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
//                        //.setPrompt("请对准二维码")// 设置提示语
//                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
//                        .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
//                        .setCaptureActivity(QrCodeActivity.class)//自定义扫码界面
//                        .initiateScan();// 初始化扫码
//                break;
        }
    }

    //发送参数
//    private void toSend() {
//        char[] message = dataApplication.getCharCam();
//        char [] arr = new char[]{'#', '0', '2','#'};
//        Utils utils =new Utils(context);
//        showMessage =  utils.addChar(arr,message);
//        if(dataApplication.sta_connect == 1 ){          //只有连接上了才能点击发送
//            ChatManager.getCM().sendCharToStream(showMessage,true);
//        }else{
//            Toast.makeText(context,"没有连接到服务器",Toast.LENGTH_SHORT).show();
//        }
//    }

    //发送参数
    private void toSend3() {
        byte[] message = dataApplication.getCharCam();
        byte [] arr = new byte[]{'#', '0', '2','#'};
        byte[] byte_showMessage =  utils.byteMerger(arr,message);

        System.out.println(utils.toHexString(byte_showMessage));
        if(dataApplication.sta_connect == 1 ){          //只有连接上了才能点击发送
//            ChatManager.getCM().sendByteToStream(byte_showMessage,true);
//            ChatManager.getCM().sendCharToStream(utils.toHexString(byte_showMessage),true);
            ChatManager.getCM().sendByteToStream(byte_showMessage,true);
        }else{
            Toast.makeText(context,"没有连接到服务器",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    char [] arr = new char[]{'#','0','4','#'};

}

package com.example.myapplication.com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class CappamActivity extends FragmentActivity implements View.OnClickListener{

    private Bundle bundle;
    private ImageView imageView;
    private TextView textView;
    private EditText editText;

    private String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower, videoSize, videoLength,
            triggerPir, triggerTimelapse, wortTime1, workTime2, workTime3, workTime4, sendMode, remoteControl, rename, overWrite, passWord,
            triggerSen;
    private Intent intent;
    private byte[] arrayByte;
    private TextView textView_cameraMode, textView_cameraFlash, textView_triggerPir, textView_timelapse,
            textView_worktime1, textView_worktime2, textView_worktime3, textView_worktime4;
    private Spinner textView_control, textView_sendmode;
    private DataApplication dataApplication;
    private int sta_name, sta_password, sta_overWrite;
    char[] value10, value11;
    private EditText edit_ip, edit_port;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.253.135";
    private static final int PORT = 21567;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private char[] chs;
    private int length;
    byte[] CamParam_t;
    byte myBurstSpeed, myShutter, myOverWrite, myBuzzer, myStaPasswd, myGps, myStaRename;//1 个字节
    short myMode, myFlashPower, myVideoRes;       //2个字节
    int myPhotoRes, myBurstNum, mySendOption, myRes1;      //4个字节
    int myVideoLength, myLanguage;         //8个字节
    private int newValue = 1;


    String message;
    Socket socket;
    private OutputStream outputStream;
    FragmentManager fragmentManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataApplication = new DataApplication(this).getDataApplication();
        dataApplication.defaultSetting();
        init();

        //一创建，自动链接服务器,并且发送#02#指令，获取信息
//        ChatManager.getCM().connect("192.168.1.224", 5001);
//        ChatManager.getCM().send("#02#");
    }

    //初始化
    private void init() {
        getCamParam();      //获取相机参数
        getEle();           //获取元素
        showCameParam();//显示相机数据
        findViewById(R.id.button_camera).setOnClickListener(this);              //点击camer mode
        findViewById(R.id.button_trigger).setOnClickListener(this);               //点击trigger
        findViewById(R.id.button_worktime).setOnClickListener(this);               //点击work time
        findViewById(R.id.button_net).setOnClickListener(this);            //点击net
//        findViewById(R.id.button_).setOnClickListener(this);                //点击control
        findViewById(R.id.button_sys).setOnClickListener(this);                  //点击rename
        findViewById(R.id.button_zxing).setOnClickListener(this);           //生成二维码
        findViewById(R.id.button_connect).setOnClickListener(this);           //生成二维码
    }

    //显示相机数据
    private void showCameParam() {
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
        if (wortTime1 == "off") {
            textView_worktime1.setText(wortTime1);
        } else {
        }
        message = wortTime1;
        textView_worktime1.setText(message);
        message = workTime2;
        textView_worktime2.setText(message);
        message = workTime3;
        textView_worktime3.setText(message);
        message = workTime4;
        textView_worktime4.setText(message);
    }

    //获取元素
    private void getEle() {
        textView_cameraMode = findViewById(R.id.textview_camera_mode);
        textView_cameraFlash = findViewById(R.id.textview_camera_flash);
        textView_triggerPir = findViewById(R.id.textview_trigger_pir);
        textView_timelapse = findViewById(R.id.textview_trigger_timelapse);
        textView_worktime1 = findViewById(R.id.textview_worktime1);
        textView_worktime2 = findViewById(R.id.textview_worktime2);
        textView_worktime3 = findViewById(R.id.textview_worktime3);
        textView_worktime4 = findViewById(R.id.textview_worktime4);
        imageView = this.findViewById(R.id.imageView_zxing);                    //生成的二维码
    }

    //处理监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zxing:                                     //生成二维码
                intent = new Intent(this, ShowQRCodeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_camera:                    //单击进入cameraMode界面
                intent = new Intent(this, CameraActivity.class);
                startActivityForResult(intent, 1);                //回执
                break;
            case R.id.button_trigger:                   //点击进入触发界面
                intent = new Intent(this, TriggerModeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_net:                   //点击进入设置net界面
                intent = new Intent(this, NetActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_worktime:                  //点击进入工作时间界面
                intent = new Intent(this, WorkTimeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_sys:
                intent = new Intent(this, SysActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_connect:
                intent = new Intent(this, ConnectScoket.class);
                startActivityForResult(intent, 1);
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


    //获取相机设置
    private void getCamParam() {
        cameraMode = dataApplication.getCameraMode();
        photoSize = dataApplication.getPhotoSize();
        photoBurst = dataApplication.getPhotoBurst();
        burstSpeed = dataApplication.getBurstSpeed();
        sendingOption = dataApplication.getSendingOption();
        shutterSpeed = dataApplication.getShutterSpeed();
        flashPower = dataApplication.getFlashPower();
        videoSize = dataApplication.getVideoSize();
        videoLength = dataApplication.getVideoLength();
        triggerPir = dataApplication.getTriggerPir();
        triggerSen = dataApplication.getTriggerSen();
        triggerTimelapse = dataApplication.getTriggerTimelapse();
        wortTime1 = dataApplication.getWortTime1();
        workTime2 = dataApplication.getWorkTime2();
        workTime3 = dataApplication.getWorkTime3();
        workTime4 = dataApplication.getWorkTime4();
        sendMode = dataApplication.getSendMode();
        remoteControl = dataApplication.getRemoteControl();
        passWord = dataApplication.getPassword();
        rename = dataApplication.getRename();
        sta_name = dataApplication.getStaRename();
        sta_password = dataApplication.getStaPassword();
        sta_overWrite = dataApplication.getOverWrite();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        init();
//        setTextView();
    }

    //返回main界面的返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView = findViewById(R.id.textview_container);
        textView.setText("扫码结果：" + requestCode + "_" + resultCode);
        //扫码结果
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                //扫码失败
            } else {
                String result = intentResult.getContents();//返回值
                textView.setText("扫码结果：" + result);
            }
        }
        if (requestCode == 1 && resultCode == 2) {
            String content = data.getStringExtra("data");
        }
    }

    //    private void connectServerWithTCPSocket(String value){
//
//    }
    private void sendWithTCPSocket(String value) {
        try {
            printWriter.print("#1#" + value);
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getWithTCPSocket() {
        InputStream inputStream = null;//获取一个输入流，接收服务端的信息
        try {
            inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//缓冲区
            String info = "";
            String temp = null;//临时变量
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("客户端接收服务端发送信息：" + info);
            }
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void connectServerWithTCPSocket(String value){
//        try {
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
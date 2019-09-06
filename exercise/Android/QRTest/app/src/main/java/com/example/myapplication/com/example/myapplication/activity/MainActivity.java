package com.example.myapplication.com.example.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataApplication;
import com.example.myapplication.PingNet;
import com.example.myapplication.PingNetEntity;
import com.example.myapplication.R;
import com.example.myapplication.Utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

import static com.example.myapplication.Utils.intToCharList;
import static com.example.myapplication.Utils.method;
import static com.example.myapplication.Utils.strToCharList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private EditText edit_ip,edit_port;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataApplication = new DataApplication(this).getDataApplication();
        dataApplication.defaultSetting();
        init();
//        setTextView();
//        connectServer("192.168.1.224", 5001);
    }

    //    创建一个socket对象，在构造时，会向服务器发送连接
    private void connectServer(String host, int port) {
        new Thread() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作
                //创建Socket对象
                try {
                    socket = new Socket(host, port);
                    outputStream = null;//获取一个输出流，向服务端发送信息
                    outputStream = socket.getOutputStream();
                    printWriter = new PrintWriter(outputStream);//将输出流包装成打印流
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //根据输入输出流和服务端连接
//                        isAvailableByPing("192.168.0.104");
            }
        }.start();
    }

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
        findViewById(R.id.button_get).setOnClickListener(this);           //获取回执
        findViewById(R.id.button_realtime).setOnClickListener(this);           //设置实时连接
        findViewById(R.id.button_other).setOnClickListener(this);           //ping ip
        findViewById(R.id.button_connect).setOnClickListener(this);           //连接ip
        findViewById(R.id.button_startServer).setOnClickListener(this);           //开启服务
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
        edit_ip = this.findViewById(R.id.edit_ip);                    //生成的二维码
        edit_port = this.findViewById(R.id.edit_port);                    //生成的二维码
    }

    io.socket.client.Socket socket2 = null;

    //处理监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zxing:                                     //生成二维码
                intent = new Intent(MainActivity.this, ShowQRCodeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_send:                  //发送信息到服务端
                String value = dataApplication.getQRCode();
//                final String wsurl="*******";
                new Thread() {
                    @Override
                    public void run() {
                            String host = "192.168.1.183";
//                        String host = "192.168.0.104";
                        int port = 5001;
                        try {
                            socket = new Socket(host, port);
                            outputStream = null;//获取一个输出流，向服务端发送信息
                            outputStream = socket.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        printWriter = new PrintWriter(outputStream);//将输出流包装成打印流
                    }

                }.start();
                break;
            case R.id.button_get:                  //从服务器获取信息
                getWithTCPSocket();
                break;
            case R.id.button_realtime:                  //实时接口
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_other:                  //其他
                Utils utils = new Utils(this);
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX:运行到了这里");
                utils.isAvailableByPing("192.168.1.224");
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_camera:                    //单击进入cameraMode界面
                intent = new  Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, 1);                //回执
                break;
            case R.id.button_trigger:                   //点击进入触发界面
                intent = new

                        Intent(MainActivity.this, TriggerModeActivity.class);

                startActivityForResult(intent, 1);
                break;
            case R.id.button_net:                   //点击进入触发界面
                intent = new

                        Intent(MainActivity.this, NetActivity.class);

                startActivityForResult(intent, 1);
                break;
            case R.id.button_worktime:                  //点击进入工作时间界面
                intent = new

                        Intent(MainActivity.this, WorkTimeActivity.class);

                startActivityForResult(intent, 1);
                break;
            case R.id.button_sys:
                intent = new

                        Intent(MainActivity.this, SysActivity.class);

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


//    //连接到socket
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

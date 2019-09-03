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
import com.example.myapplication.R;
import com.example.myapplication.Utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;import java.lang.reflect.Array;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.253.135";
    private static final int PORT = 21567;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataApplication = new DataApplication().getDataApplication();
        dataApplication.defaultSetting();
        init();
    }

    String message;

    private void init() {
        getCamParam();

        textView_cameraMode = findViewById(R.id.textview_camera_mode);
        textView_cameraFlash = findViewById(R.id.textview_camera_flash);
        textView_triggerPir = findViewById(R.id.textview_trigger_pir);
        textView_timelapse = findViewById(R.id.textview_trigger_timelapse);
        textView_worktime1 = findViewById(R.id.textview_worktime1);
        textView_worktime2 = findViewById(R.id.textview_worktime2);
        textView_worktime3 = findViewById(R.id.textview_worktime3);
        textView_worktime4 = findViewById(R.id.textview_worktime4);
        textView_sendmode = findViewById(R.id.textview_sendmode);
        textView_control = findViewById(R.id.textview_control);

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


        imageView = this.findViewById(R.id.imageView_zxing);                    //生成的二维码
        findViewById(R.id.button_camera).setOnClickListener(this);              //点击camer mode
        findViewById(R.id.button_trigger).setOnClickListener(this);               //点击trigger
        findViewById(R.id.button_worktime).setOnClickListener(this);               //点击work time
        findViewById(R.id.button_sendmode).setOnClickListener(this);            //点击sendmode
        findViewById(R.id.button_control).setOnClickListener(this);                //点击control
        findViewById(R.id.button_sys).setOnClickListener(this);                  //点击rename
        findViewById(R.id.button_zxing).setOnClickListener(this);           //生成二维码
    }



    byte[] CamParam_t;
    byte myBurstSpeed, myShutter, myOverWrite, myBuzzer, myStaPasswd, myGps, myStaRename;//1 个字节
    short myMode, myFlashPower, myVideoRes;       //2个字节
    int myPhotoRes, myBurstNum, mySendOption, myRes1;      //4个字节
    long myVideoLength, myLanguage;         //8个字节


    //    byte[] myMode = new byte[2];//Mode
//    byte[] myFlashPower = new byte[2];//Mode
//    byte[] myShutter = new byte[1];//Mode
//    byte[] myBurstSpeed = new byte[1];//Mode
//    byte[] myVideoRes = new byte[2];//Mode
//    byte[] myPhotoRes = new byte[4];//Mode
//    byte[] myBurstNum = new byte[4];//BurstNum
//    byte[] myVideoLength= new byte[6];
//    byte[] myRename = new byte[1];
//    byte[] myOverWrite=new byte[1];
//
//    byte[] mySendOption=new byte[4];
//    byte[] myRes1=new byte[4];
//
//    byte[] myLanguage=new byte[5];
//    byte[] myGps=new byte[1];
//    byte[] myPasswd=new byte[1];
//    byte[] myBuzzer=new byte[1];
//
//    byte[] myPassword = new byte[4];
//    byte[] myCamName = new byte[9];
    private int newValue = 1;

    //处理监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zxing:                                     //生成二维码
                String showValue = getValue();
                String count = showValue;
                if (TextUtils.isEmpty(count)) {                         //如果
                    Toast.makeText(MainActivity.this, "请输入内容", Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println(count);

//                textView = findViewById(R.id.textview_container);
//                textView.setText(count);
                //生成二维码并显示在imageView上，宽和高都为600
                imageView.setImageBitmap(generateBitmap(count, 600, 600));
                break;
            case R.id.button_camera:
                intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, 1);                //回执
                break;
            case R.id.button_trigger:
                intent = new Intent(MainActivity.this, TriggerModeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_worktime:
                intent = new Intent(MainActivity.this, WorkTimeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button_sys:
                intent = new Intent(MainActivity.this, SysActivity.class);
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
//        startActivityForResult(intent, 1);
    }

    //把字符数据转成二进制字符数据；
    private String getValue() {
        switch (cameraMode) {
            case "photo":
                newValue = 0;
                break;
            case "video":
                newValue = 1;
                break;
        }
        myMode = (byte) newValue;
        switch (flashPower) {
            case "Low":
                newValue = 0;
                break;
            case "Normal":
                newValue = 1;
                break;
            case "High":
                newValue = 2;
                break;
        }
        myFlashPower = (byte) newValue;
        switch (shutterSpeed) {
            case "Normal":
                newValue = 0;
                break;
            case "Fast":
                newValue = 0;
                break;
        }
        myShutter = (byte) newValue;
        switch (burstSpeed) {
            case "Fast(200ms)":
                newValue = 0;
                break;
            case "Show(500ms)":
                newValue = 1;
                break;
        }
        myBurstSpeed = (byte) newValue;
        switch (videoSize) {
            case "1080P":
                newValue = 0;
                break;
            case "720P":
                newValue = 1;
                break;
            case "WVGA":
                newValue = 1;
                break;
        }
        myVideoRes = (byte) newValue;
        switch (photoSize) {
            case "3MP":
                newValue = 0;
                break;
            case "5MP":
                newValue = 1;
                break;
            case "8MP":
                newValue = 2;
                break;
//                    case "12MP":
//                        newValue = 3;break;
//                    case "16Mp":
//                        newValue = 4;break;
//                    case "20MP":
//                        newValue = 5;break;
        }
        myPhotoRes = (byte) newValue;
        switch (photoBurst) {
            case "1photo":
                newValue = 0;
                break;
            case "2photos":
                newValue = 1;
                break;
            case "3photos":
                newValue = 2;
                break;
        }
        myBurstNum = (byte) newValue;
        switch (videoLength) {
            case "5sec":
                newValue = 0;
                break;
            case "10sec":
                newValue = 1;
                break;
            case "15sec":
                newValue = 2;
                break;
            case "20sec":
                newValue = 3;
                break;
            case "25sec":
                newValue = 4;
                break;
            case "30sec":
                newValue = 5;
                break;
            case "35sec":
                newValue = 6;
                break;
            case "40sec":
                newValue = 7;
                break;
            case "45sec":
                newValue = 8;
                break;
            case "50sec":
                newValue = 9;
                break;
            case "55sec":
                newValue = 10;
                break;
            case "60sec":
                newValue = 11;
                break;
        }
        myVideoLength = (byte) newValue;
        switch (sendingOption) {
            case "1st":
                newValue = 0;
                break;
            case "2st":
                newValue = 1;
                break;
            case "3st":
                newValue = 2;
                break;
        }
        mySendOption = (byte) newValue;
        myStaRename = (byte) sta_name;
        myStaPasswd = (byte) sta_password;
        myOverWrite = (byte) sta_overWrite;
        String newpassWord,newRename;
        Utils utils = new Utils(this);
        if(sta_password == 1){
            newpassWord = utils.toBinaryString(passWord);
        }else{
            newpassWord = toFullBinaryString(0,9);
        }
        if(sta_name == 1){
            newRename = utils.toBinaryString(rename);
        }else{
            newRename = toFullBinaryString(0,9);
        }

        Log.i("当前password",""+passWord);
        Log.i("当前ren",""+rename);
        String value = toFullBinaryString(myMode, 2) + toFullBinaryString(myFlashPower, 2) +
                toFullBinaryString(myShutter, 1) +
                toFullBinaryString(myBurstSpeed, 1) + toFullBinaryString(myVideoRes, 2) +
                toFullBinaryString(myPhotoRes, 4) +
                toFullBinaryString(myBurstNum, 4) + toFullBinaryString(myVideoLength, 6) +
                toFullBinaryString(mySendOption, 4) + toFullBinaryString(myRes1, 4) +
                toFullBinaryString(myLanguage, 5) + toFullBinaryString(myGps,1) +
                toFullBinaryString(myStaPasswd,1) + toFullBinaryString(myBuzzer,1)+
                newpassWord + newRename
                ;
        return value;

    }

    /**
     * 生成固定大小的二维码(不需网络权限)
     *
     * @param content 需要生成的内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    private char[] chs;
    private int length;
    //传入一个是变成二进制的数，一个是字节数；
    //把数值转换成二进制形式的字符串；
    public String toFullBinaryString(long num,int type) {
        length = type * 8;
//        System.out.println(length);
        chs = new char[length];
        for (int i = 0; i < length;i++) {
            chs[length - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
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



}
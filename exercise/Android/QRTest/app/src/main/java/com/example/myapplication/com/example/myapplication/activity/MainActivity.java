package com.example.myapplication.com.example.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle bundle;
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
//    private Switch switcher;
//    private int camera_mode, trigger, raname, over_write, send_mode, work_time, remote_control;
    private String cameraMode,photoSize,photoBurst,burstSpeed,sendingOption,shutterSpeed,flashPower,videoSize,videoLength,
            triggerPir,triggerTimelapse,wortTime1,workTime2,workTime3,workTime4,sendMode,remoteControl,rename,overWrite,
            triggerSen;
    private Intent intent;

    private TextView textView_cameraMode,textView_cameraFlash,textView_triggerPir,textView_timelapse,
            textView_worktime1,textView_worktime2,textView_worktime3,textView_worktime4
            ;
    private Spinner textView_control,textView_sendmode;
    private DataApplication dataApplication;
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
        rename = dataApplication.getRename();

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

        if(cameraMode == "photo"){
            message = cameraMode + " ( "+photoSize+" | "+photoBurst+" ) ";
            textView_cameraMode.setText(message);
            message =  "Flash Power( "+flashPower+" )";
            textView_cameraFlash.setText(message);
        }else{
            message = cameraMode ;
            textView_cameraMode.setText(message);
            message =  " ( "+videoSize+" | "+ videoLength+"  ) ";
            textView_cameraFlash.setText(message);
        }
        if(triggerSen == "off"){
            message = "off";
            textView_triggerPir.setText(message);
        }else{
            message = "PIR ( "+triggerSen +" | "+ triggerPir+" )";
            textView_triggerPir.setText(message);
        }
        if(triggerTimelapse == "off"){
            textView_timelapse.setText(triggerTimelapse);
        }else{
            message = "TimeLapse ( "+triggerTimelapse+" )";
            textView_timelapse.setText(message);
        }

        if(wortTime1 == "off"){
            textView_worktime1.setText(wortTime1);
        }else{
        }


        message = wortTime1;
        textView_worktime1.setText(message);
        message = workTime2;
        textView_worktime2.setText(message);
        message = workTime3;
        textView_worktime3.setText(message);
        message = workTime4;
        textView_worktime4.setText(message);
//        message = sendMode;??
//        textView_sendmode.setText(message);
//        message = remoteControl;
//        textView_control.(message);
//        message = rename;
//        textView_rename.setText(message);

        imageView = this.findViewById(R.id.imageView_zxing);                    //生成的二维码
        findViewById(R.id.button_camera).setOnClickListener(this);              //点击camer mode
        findViewById(R.id.button_trigger).setOnClickListener(this);               //点击trigger
        findViewById(R.id.button_worktime).setOnClickListener(this);               //点击work time
        findViewById(R.id.button_sendmode).setOnClickListener(this);            //点击sendmode
        findViewById(R.id.button_control).setOnClickListener(this);                //点击control
        findViewById(R.id.button_sys).setOnClickListener(this);                  //点击rename
        findViewById(R.id.button_zxing).setOnClickListener(this);           //生成二维码
    }


    //处理监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zxing:                                     //生成二维码
//                String count = editText.getText().toString().trim();
                String count = "哈哈哈哈哈哈哈哈";
                if (TextUtils.isEmpty(count)) {                         //如果
                    Toast.makeText(MainActivity.this, "请输入内容", Toast.LENGTH_LONG).show();
                    return;
                }
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
                Toast.makeText(this,"Sys",Toast.LENGTH_SHORT).show();
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


//　　　　　　tv2.setText(content);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        init();
    }
}
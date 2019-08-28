package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.QrCodeActivity;
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
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private Switch switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switcher = findViewById(R.id.switcher);                                    //开关
        imageView = this.findViewById(R.id.imageView_zxing);                    //生成的二维码
//        textView = this.findViewById(R.id.textview_zxing);                  //扫描二维码后生成的内容
//        editText = this.findViewById(R.id.edittext_zxing);

//        findViewById(R.id.button_start).setOnClickListener(this);
        findViewById(R.id.button_camera).setOnClickListener(this);              //点击camer mode
        findViewById(R.id.button_trigger).setOnClickListener(this);               //点击trigger
        findViewById(R.id.button_worktime).setOnClickListener(this);               //点击work time
        findViewById(R.id.button_sendmode).setOnClickListener(this);            //点击sendmode
        findViewById(R.id.button_control).setOnClickListener(this);                //点击control
        findViewById(R.id.button_rename).setOnClickListener(this);                  //点击rename
//        findViewById(R.id.button_overwrite).setOnClickListener(this);                  //点击overwrite
        findViewById(R.id.button_zxing).setOnClickListener(this);           //生成二维码
        onCheckChange();                                                               //点击overwrite
    }

    public int over_write;

    //overwrite选择器
    public void onCheckChange() {
        //overwrite选择器
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if (isChecked) {
                    switcher.setChecked(true);
                    over_write = 1;
//                    System.out.println("ture");
                } else {
                    over_write = 0;
//                    System.out.println("false");
                }
            }
        };
        switcher.setOnCheckedChangeListener(listener);
    }

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
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                startActivityForResult(intent, 1);


                setContentView(R.layout.camera_mode);
                break;
            case R.id.button_trigger:
                setContentView(R.layout.trigger);
                break;
            case R.id.button_worktime:
                setContentView(R.layout.worktime);
                break;
            case R.id.button_sendmode:
                setContentView(R.layout.sendmode);
                break;
            case R.id.button_control:
                setContentView(R.layout.remote_control);
                break;
            case R.id.button_rename:
                setContentView(R.layout.rename);
                break;
            case R.id.button_overwrite:
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

}
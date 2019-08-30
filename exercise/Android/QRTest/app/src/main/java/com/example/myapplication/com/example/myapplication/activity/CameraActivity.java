package com.example.myapplication.com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends AppCompatActivity  {
    private String content = "我是传回来的值";
    private Switch switcher;
    private List<String> list_photoSize = new ArrayList<String>();
    private List<String> list_photoBurst = new ArrayList<String>();
    private List<String> list_burstSpeed = new ArrayList<String>();
    private List<String> list_sendingOption = new ArrayList<String>();
    private List<String> list_shutterSpeed = new ArrayList<String>();
    private List<String> list_flashPower = new ArrayList<String>();
    private Spinner spinner_photoSize, spinner_photoBurst, spinner_burstSpeed, spinner_sendingOption, spinner_shutterSpeed, spinner_flashPower;
    //    private Spinner spinner_photoBurst;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_mode);
        init();
    }

    //初始化操作
    private void init() {
        chooseCameraMode();

        spinner_photoSize = findViewById(R.id.spinner_photoSize);
        spinner_photoBurst = findViewById(R.id.spinner_photoBurst);
        spinner_burstSpeed = findViewById(R.id.spinner_burstSpeed);

        //往列表中添加选项
        list_photoSize.add("3MP");
        list_photoSize.add("5MP");
        list_photoSize.add("8MP");
        list_photoSize.add("12MP");
        list_photoSize.add("16Mp");
        list_photoSize.add("20MP");
        //photo size选择器选择监听
        listSelect(list_photoSize, spinner_photoSize);

        //往列表中添加选项
        list_photoBurst.add("1photo");
        list_photoBurst.add("2photos");
        list_photoBurst.add("3photos");
        list_photoBurst.add("4photos");
        list_photoBurst.add("5photos");
        list_photoBurst.add("6photos");
        list_photoBurst.add("7photos");
        list_photoBurst.add("8photos");
        list_photoBurst.add("9photos");
        list_photoBurst.add("10photos");
        //photo burst选择器选择监听
        listSelect(list_photoBurst, spinner_photoBurst);

        list_burstSpeed.add("Fast(200ms)");
        list_burstSpeed.add("Show(500ms)");
        listSelect(list_burstSpeed, spinner_burstSpeed);

        spinner_sendingOption = findViewById(R.id.spinner_sendingOption);
        list_sendingOption.add("1photo");
        list_sendingOption.add("2photos");
        list_sendingOption.add("3photos");
        list_sendingOption.add("4photos");
        list_sendingOption.add("5photos");
        list_sendingOption.add("6photos");
        list_sendingOption.add("7photos");
        list_sendingOption.add("8photos");
        list_sendingOption.add("9photos");
        list_sendingOption.add("10photos");
        listSelect(list_sendingOption, spinner_sendingOption);

        spinner_shutterSpeed = findViewById(R.id.spinner_shutterSpeed);
        list_shutterSpeed.add("Normal");
        list_shutterSpeed.add("Fast");
        listSelect(list_shutterSpeed, spinner_shutterSpeed);


        spinner_flashPower = findViewById(R.id.spinner_flashPower);
        list_flashPower.add("Low");
        list_flashPower.add("Normal");
        list_flashPower.add("High");
        listSelect(list_flashPower, spinner_flashPower);

    }

    private RadioGroup rg;
    private RadioButton rb_photo, rb_video;
    //在cameraMode里面进行的选择
    private void chooseCameraMode() {
        rg = (RadioGroup) findViewById(R.id.rg_sex);
        rb_photo = (RadioButton) findViewById(R.id.rb_photo);
        rb_video = (RadioButton) findViewById(R.id.rb_video);
        //注意是给RadioGroup绑定监视器
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 选中状态改变时被触发
                switch (checkedId) {
                    case R.id.rb_video:
                        // 当用户选择video时
                        Log.i("video", "当前用户选择:video");
                        break;
                    case R.id.rb_photo:
                        // 当用户选择photo时
                        Log.i("photo", "当前用户选择:photo");
                        break;
                }
            }
        });
    }

    //下拉列表的选择
    public void listSelect(List<String> list_ele, Spinner spinnerEle) {
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_ele);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnerEle.setAdapter(adapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
//        spinnerEle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                System.out.println(view);
////                System.out.println(i);
////                System.out.println(l);
//            }
//        });
    }


    //CameraMode单选框按钮监听
//    class MyRadioButtonListener implements RadioGroup.OnCheckedChangeListener {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            // 选中状态改变时被触发
//            switch (checkedId) {
//                case R.id.rb_FeMale:
//                    // 当用户选择女性时
//                    Log.i("video", "当前用户选择:video");
//                    break;
//                case R.id.rb_Male:
//                    // 当用户选择男性时
//                    Log.i("photo", "当前用户选择:photo");
//                    break;
//            }
//        }
//    }


//    @Override
//    public void onClick(View view) {
//        // TODO Auto-generated method stub
//        Intent data = new Intent();
//        data.putExtra("data", content);
//        setResult(2, data);
//        finish();
//    }

    //overwrite选择器
    public void onCheckChange() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if (isChecked) {
                    switcher.setChecked(true);
                    System.out.println("photo");
                } else {
                    System.out.println("video");
                }
            }
        };
        switcher.setOnCheckedChangeListener(listener);
    }


}

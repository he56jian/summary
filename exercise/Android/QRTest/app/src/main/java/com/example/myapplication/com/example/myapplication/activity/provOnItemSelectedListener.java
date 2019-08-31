package com.example.myapplication.com.example.myapplication.activity;

import android.view.View;
import android.widget.AdapterView;


//spinner选择器的监听器
class provOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    private String sInfo;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //获取选择的项的值
         sInfo = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        sInfo = adapterView.getItemAtPosition(0).toString();
    }


}

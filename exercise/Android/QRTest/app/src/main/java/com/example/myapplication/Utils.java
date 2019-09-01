package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class Utils {
    private Context context;

    public Utils(Context context) {
        this.context = context;
        dataApplication = new DataApplication().getDataApplication();
    }

    private DataApplication dataApplication;
    private ArrayAdapter<String> adapter;
    private String sInfo;

    //下拉列表的选择
    public void listSelect(final List<String> list_ele, Spinner spinnerEle, final String name) {
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, list_ele);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnerEle.setAdapter(adapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        spinnerEle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //获取选择的项的值
                sInfo = adapterView.getItemAtPosition(i).toString();
//                    app.setData(name,sInfo);
                dataApplication.setValue(name, sInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //获取选择的项的值
                sInfo = adapterView.getItemAtPosition(0).toString();
//                    data.setData(name,sInfo);
                dataApplication.setValue(name, sInfo);
            }
        });
    }


}
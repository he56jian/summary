package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.com.example.myapplication.activity.MainActivity;

import java.util.ArrayList;
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



    private static final String BIN_SEPARATOR = " ";
    //把字符串转成二进制字符串
    public static String toBinaryString(String str) {
        if (str == null) return null;
        StringBuffer sb = new StringBuffer();           //创建一个StringBuffer类
        byte[] bytes = str.getBytes();                  //把字符串转成一个个字符的ASCII码值
        for (byte aByte : bytes) {
            String a = Integer.toBinaryString(aByte);
            sb.append(Integer.toBinaryString(aByte));
        }
        return sb.toString();
    }

    //把字符串转成字符数组
//    public static char[] stringToCharList(String str) {
//        if (str == null) return null;
//        StringBuffer sb = new StringBuffer();           //创建一个StringBuffer类
//        byte[] bytes = str.getBytes();                  //把字符串转成一个个字符的ASCII码值
//        for (byte aByte : bytes) {
//            String a = Integer.toBinaryString(aByte);
//            sb.append(Integer.toBinaryString(aByte));
//        }
//    }

    //把传进来的值转成对应字符数组
    public static char[] strToCharList(Object object){
        return null;
    }

    //合并字符数组
    public static char[] method(String now, List<char[]> list) {
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).length;j++)
                //获取了每一个char[]
                now += list.get(i)[j];
        }
        char[] result = now.toCharArray();
        return result;
    }

    //把字符串转成字符数组
    public static char[] strToCharList(String str){
        byte[] bytes = str.getBytes();
        String ret = "";
        for(int i=0;i< bytes.length;i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length()==1){
                hex = "0" + hex;
            }
            ret+=hex;
        }
        System.out.println(ret);
        char[] result = ret.toCharArray();
        return result;
    }
    //把整型转成字符数组
    public static char[] intToCharList(int num){
        String hex = num+"";
        if(hex.length() == 1){
            hex = "0"+hex;
        }
        char[] result = hex.toCharArray();
        return result;
    }


        //把字符串转成二进制数
    public static byte[] hex2byte(String str) {
        if (str == null){
            return null;
        }
        str = str.trim();
        int len = str.length();

        if (len == 0 || len % 2 == 1){
            return null;
        }
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    // 二进制转字符串
    public static String byte2hex(byte[] b)
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1){
                sb.append("0" + stmp);
            }else{
                sb.append(stmp);
            }

        }
        return sb.toString();
    }

    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     **/
    public static String byteArrToBinStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }

    //ping ip查看情况
    private void isAvailableByPing(String ip) {
        //网络操作应在子线程中操作，避免阻塞UI线程，导致ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                PingNetEntity pingNetEntity = new PingNetEntity(ip, 3, 5, new StringBuffer());
                pingNetEntity = PingNet.ping(pingNetEntity);
                String msg = "连接时间:"+pingNetEntity.getPingTime()+";"+"连接结果："+pingNetEntity.isResult();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                Log.i("testPing", pingNetEntity.getIp());
                Log.i("testPing", "time=" + pingNetEntity.getPingTime());
                Log.i("testPing", pingNetEntity.isResult() + "");
            }
        }).start();
    }



}
package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.Database.DataApplication;
import com.example.myapplication.R;

public class ConnectScoket extends Activity implements View.OnClickListener {
    private DataApplication dataApplication;
    private EditText edit_ip,edit_port;
    private TextView textview_connect;
    private String host = "192.168.1.188";
    private int port = 12234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);

//        dataApplication = new DataApplication(this).getDataApplication();
//        edit_ip = this.findViewById(R.id.edit_ip);                    //ip编辑器
//        edit_port = this.findViewById(R.id.edit_port);                    //端口编辑
//        textview_connect = this.findViewById(R.id.textview_connect);                    //端口编辑
//
//        findViewById(R.id.button_send).setOnClickListener(this);           //发送监听
//        findViewById(R.id.button_get).setOnClickListener(this);           //获取回执
//        findViewById(R.id.button_realtime).setOnClickListener(this);           //设置实时连接
//        findViewById(R.id.button_other).setOnClickListener(this);           //ping ip
//        findViewById(R.id.button_tcp_connec).setOnClickListener(this);           //tcp连接ip
//        findViewById(R.id.button_udp_connect).setOnClickListener(this);           //udp连接ip
//        findViewById(R.id.button_start_udp_Server).setOnClickListener(this);           //开启服务
//        findViewById(R.id.button_start_tcp_Server).setOnClickListener(this);           //开启tcp服务
    }

//    char [] arr = new char[]{'#','0','1','#'};
//    @Override
    public void onClick(View view) {
//        switch (view.getId()){
////            case R.id.button_send:                  //发送信息到服务端
////                String value = dataApplication.getQRCode();
////                new Thread(){
////                    @Override
////                    public void run() {
////                        ChatManager.getCM().send("#01#"+value);
////                    }
////                }.start();
////                break;
//            case R.id.button_tcp_connec:
//                ChatManager.getCM().connect(this,host,port);
////                new Thread() {
////                    @Override
////                    public void run() {
////                        try {
////                            System.out.println("链接tcp准备：ip:"+host);
////                            Socket tcp_socket = new Socket(host, 12234);
////                            PrintWriter write = new PrintWriter(new OutputStreamWriter(tcp_socket.getOutputStream()));
////                            System.out.println("链接");
////                            BufferedReader reader = new BufferedReader(new InputStreamReader(tcp_socket.getInputStream()));
////                            OutputStream outputStream = tcp_socket.getOutputStream();
////                            char[] lime=new char[1024];
////                            String line;
////                            outputStream.write(123);
//////                            new Thread(){
//////                                @Override
//////                                public void run() {
//////                                    //循环接收数据
//////                                    while ((line = reader.readLine()) != null) {
//////                                        System.out.println(reader.read(lime,0,1024));
//////                                        System.out.println("收到"+host+"发来的数据："+line);
//////                                    }
//////                                    outputStream.flush();
//////                                    write.close();
//////                                    reader.close();
//////                                    write = null;
//////                                    reader = null;
//////                                }
//////                            }.start();
////                        } catch (IOException e) {
////                            System.out.println("链接失败");
////                            e.printStackTrace();
////                        }
////                    }
////                }.start();
////                    InputStreamReader inputStream = new InputStreamReader(tcp_socket.getInputStream());
////                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(tcp_socket.getOutputStream());
////                    int temp = 0000000000000000;
////
//////                    while((temp = inputStream.read())!=0){
////                        outputStreamWriter.write(temp);
//////                    }
////                    outputStreamWriter.flush();
//                break;
//            case R.id.button_udp_connect:
//                try {
//                    DatagramSocket datagramSocket = new DatagramSocket(5001);
//                    InetAddress serverAddress = InetAddress.getByName("192.168.1.224");
//                    String str = "11222223232323232323232323232";
//                    byte data[] = str.getBytes();
//                    DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,5001);
//                    datagramSocket.send(packet);
//                } catch (SocketException e) {
//                    System.out.println("创建Socket失败");
//                    e.printStackTrace();
//                } catch (UnknownHostException e) {
//                    System.out.println("无识别的地址");
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    System.out.println("IO异常");
//                    e.printStackTrace();
//                }
//                break;
//            case R.id.button_start_tcp_Server:
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            ServerSocket serverSocket = new ServerSocket(12234);
//                            Socket server_socket = serverSocket.accept();
//                            System.out.println("链接到了这里");
////                            server_socket.getOutputStream().write(1232);
//                            PrintWriter pout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server_socket.getOutputStream())), true);
////                            server_socket.getInputStream();
//                            BufferedReader br = new BufferedReader(new InputStreamReader(server_socket.getInputStream()));
//                            System.out.println("输出流："+pout);
//                            System.out.println("输入流："+br);
//                            String line;
//                            char[] char_buff = new char[1024];
//                                while((line = br.readLine())!=null){
//                                System.out.println("客户端发过来的信息："+line);
////                                pout.println("1122233334455");
////                                pout.println("\n");
//                            }
//                            pout.close();
//                            br.close();
//                            pout = null;
//                            br =null ;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();
//
//                break;
//            case R.id.button_start_udp_Server:
//                break;
//
//            case R.id.button_get:                  //从服务器获取信息
////                getWithTCPSocket();
//                break;
//            case R.id.button_realtime:                  //实时接口
//                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.button_other:                  //其他
//                Utils utils = new Utils(this);
//                host = edit_ip.getText().toString();
//                if(host.equals("")){
//                    host = "192.168.1.224";         //设置默认
//                }
//                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX:运行到了这里,ip是" + host);
//                utils.isAvailableByPing(host);
//                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
//                break;
//        }
    }
}

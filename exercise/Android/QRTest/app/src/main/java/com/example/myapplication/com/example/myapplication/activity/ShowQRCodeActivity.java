package com.example.myapplication.com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils;

public class ShowQRCodeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qrcode);

        ImageView imageView = findViewById(R.id.imageView_zxing);
        TextView textView = findViewById(R.id.text_zxing);

        DataApplication dataApplication = new DataApplication(this).getDataApplication();
        Utils utils = new Utils(this);
        String count = dataApplication.getQRCode();
        textView.setText(count);
        imageView.setImageBitmap(utils.generateBitmap(count, 600, 600));
    }


}

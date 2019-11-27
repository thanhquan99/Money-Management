package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViTienActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvViTien,tvChiTieu,tvSoDu;
    ImageButton imgViTien,imgAdd,imgChiTieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_tien);
        Widget();
    }
    public void Widget(){
        tvChiTieu = findViewById(R.id.tv_chi_tieu);
        tvSoDu = findViewById(R.id.tv_so_du);
        tvViTien = findViewById(R.id.tv_vi_tien);

        imgViTien = findViewById(R.id.img_btn_vi_tien);
        imgViTien.setOnClickListener(this);
        imgAdd = findViewById(R.id.img_btn_add);
        imgAdd.setOnClickListener(this);
        imgChiTieu = findViewById(R.id.img_btn_chi_tieu);
        imgChiTieu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_chi_tieu:
                Intent intent1 = new Intent(ViTienActivity.this,ChiTieuActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_btn_add:
                Intent intent2 = new Intent(ViTienActivity.this,AddActivity.class);
                startActivity(intent2);
                break;
            case R.id.img_btn_vi_tien:
                break;
        }
    }
}

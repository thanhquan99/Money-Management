package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quanlichitieu1.data.DBManager;

import java.text.NumberFormat;
import java.util.Locale;

public class ViTienActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvChiTieu,tvAnUong,tvTheThao,tvGiaiTri;
    ImageButton imgViTien,imgAdd,imgChiTieu,btnAnUong,btnTheThao,btnGiaiTri;
    public final DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_tien);
        Widget();

    }
    public void Widget(){
        tvChiTieu = findViewById(R.id.tv_chi_tieu);

        imgViTien = findViewById(R.id.img_btn_vi_tien);
        imgViTien.setOnClickListener(this);
        imgAdd = findViewById(R.id.img_btn_add);
        imgAdd.setOnClickListener(this);
        imgChiTieu = findViewById(R.id.img_btn_chi_tieu);
        imgChiTieu.setOnClickListener(this);

        btnAnUong = findViewById(R.id.btn_an_uong);
        btnTheThao = findViewById(R.id.btn_the_thao);
        btnGiaiTri = findViewById(R.id.btn_giai_tri);
        btnAnUong.setOnClickListener(this);
        btnTheThao.setOnClickListener(this);
        btnGiaiTri.setOnClickListener(this);

        tvAnUong = findViewById(R.id.tv_an_uong);
        tvGiaiTri = findViewById(R.id.tv_giai_tri);
        tvTheThao = findViewById(R.id.tv_the_thao);
        tvAnUong.setText(HienThiTien(dbManager.getAnUong())+" VND");
        tvGiaiTri.setText(HienThiTien(dbManager.getGiaiTri())+" VND");
        tvTheThao.setText(HienThiTien(dbManager.getTheThao())+" VND");
        tvChiTieu.setText("Σ"+HienThiTien(dbManager.getTongChiTieu())+" VND");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_chi_tieu:
                Intent intent1 = new Intent(ViTienActivity.this,OptionActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_right,R.anim.anim_exit_to_right);
                break;
            case R.id.img_btn_add:
                Intent intent2 = new Intent(ViTienActivity.this,AddActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.anim_enter_to_right,R.anim.anim_exit_to_right);
                break;
            case R.id.img_btn_vi_tien:
                break;
            case R.id.btn_an_uong:
                Intent intent3 = new Intent(ViTienActivity.this,AnUongActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
                break;
            case R.id.btn_the_thao:
                Intent intent4 = new Intent(ViTienActivity.this,TheThaoActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
                break;
            case R.id.btn_giai_tri:
                Intent intent5 = new Intent(ViTienActivity.this,GiaiTriActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
                break;
        }
    }
    public String HienThiTien(int tien){
        Locale localeVN= new Locale("vi","VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String string = currencyVN.format(tien);
        string = string.substring(1,string.length());
        return string;
    }
}

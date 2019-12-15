package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnChiTieu,btnBieuDo,btnHome,btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        GUI();
    }
    public void GUI(){
        btnChiTieu = findViewById(R.id.img_btn_chi_tieu);
        btnAdd = findViewById(R.id.img_btn_add);
        btnBieuDo = findViewById(R.id.img_btn_bieu_do);
        btnHome = findViewById(R.id.img_btn_vi_tien);

        btnHome.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnBieuDo.setOnClickListener(this);
        btnChiTieu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_add:
                Intent intent1 = new Intent(OptionActivity.this,AddActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_left,R.anim.anim_exit_to_left);
                break;
            case R.id.img_btn_chi_tieu:
                Intent intent2 = new Intent(OptionActivity.this,ChiTieuActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
                break;
            case R.id.img_btn_vi_tien:
                Intent intent3 = new Intent(OptionActivity.this,ViTienActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.anim_enter_to_left,R.anim.anim_exit_to_left);
                break;
            case R.id.img_btn_bieu_do:
                Intent intent4 = new Intent(OptionActivity.this,BieuDoActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
                break;
        }
    }
}

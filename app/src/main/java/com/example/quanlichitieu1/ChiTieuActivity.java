package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ChiTieuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgViTien,imgAdd,imgChiTieu;
    private ListView lvResult;

    private List<Contact> arrayContact;
    private ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu);
        Widget();
        getData();
    }
    public void Widget(){
        imgViTien = findViewById(R.id.img_btn_vi_tien);
        imgViTien.setOnClickListener(this);
        imgAdd = findViewById(R.id.img_btn_add);
        imgAdd.setOnClickListener(this);
        imgChiTieu = findViewById(R.id.img_btn_chi_tieu);
        imgChiTieu.setOnClickListener(this);
        lvResult = findViewById(R.id.lv_result);

        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this,R.layout.item_listview,arrayContact);
        lvResult.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_chi_tieu:
                break;
            case R.id.img_btn_add:
                Intent intent1 = new Intent(ChiTieuActivity.this,AddActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_btn_vi_tien:
                Intent intent2 = new Intent(ChiTieuActivity.this,ViTienActivity.class);
                startActivity(intent2);
                break;
        }

    }
    public void getData(){
        Intent intent = getIntent();
        int Tien = intent.getIntExtra(AddActivity.TIEN,0);
        int ViTriHangMuc = intent.getIntExtra(AddActivity.VI_TRI_HANG_MUC,0);
        String HangMuc = intent.getStringExtra(AddActivity.HANG_MUC);
        String Time = intent.getStringExtra(AddActivity.TIME);
        if(Tien != 0){
            Contact contact = new Contact(Tien+"",HangMuc,Time,ViTriHangMuc);
            arrayContact.add(contact);
            adapter.notifyDataSetChanged();
        }
    }
}

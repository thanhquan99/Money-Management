package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.data.DBManager;
import com.example.quanlichitieu1.model.ChiTieu;

import java.util.ArrayList;
import java.util.List;

public class ChiTieuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgViTien,imgAdd,imgChiTieu;
    private ListView lvResult;

    private List<ChiTieu> chiTieuList;
    private ContactAdapter contactAdapter;
    public final DBManager dbManager = new DBManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu);
        Widget();
        chiTieuList = dbManager.getAllChiTieu();
        setAdapter();
        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTieu chitieu =chiTieuList.get(position);
                Intent intent = new Intent(ChiTieuActivity.this,DeleteUpdateActivity.class);
                intent.putExtra(DeleteUpdateActivity.ID,chitieu.getmID());
                intent.putExtra(DeleteUpdateActivity.TIEN,chitieu.getmTien());
                intent.putExtra(DeleteUpdateActivity.HANG_MUC,chitieu.getmHangMuc());
                intent.putExtra(DeleteUpdateActivity.VI_TRI_HANG_MUC,chitieu.getmViTriHangMuc());
                intent.putExtra(DeleteUpdateActivity.TIME,chitieu.getmTime());
                startActivity(intent);
            }
        });
    }
    public void Widget(){
        imgViTien = findViewById(R.id.img_btn_vi_tien);
        imgViTien.setOnClickListener(this);
        imgAdd = findViewById(R.id.img_btn_add);
        imgAdd.setOnClickListener(this);
        imgChiTieu = findViewById(R.id.img_btn_chi_tieu);
        imgChiTieu.setOnClickListener(this);
        lvResult = findViewById(R.id.lv_result);


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
    public void setAdapter(){
        if(contactAdapter==null){
            contactAdapter = new ContactAdapter(this,R.layout.item_listview,chiTieuList);
            lvResult.setAdapter(contactAdapter);
        }else{
            contactAdapter.notifyDataSetChanged();
            lvResult.setSelection(contactAdapter.getCount()-1);
        }
    }

}

package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.data.DBManager;
import com.example.quanlichitieu1.model.ChiTieu;

import java.util.List;

public class TheThaoActivity extends AppCompatActivity {
    private Button btnBack;
    private ListView lvResult;

    private List<ChiTieu> chiTieuList;
    private ContactAdapter contactAdapter;
    public final DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_thao);
        Widget();
        chiTieuList = dbManager.takeTheThao();
        setAdapter();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TheThaoActivity.this,ViTienActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_up,R.anim.anim_exit_to_up);
            }
        });

    }
    public void Widget(){
        lvResult = findViewById(R.id.lv_result);
        btnBack = findViewById(R.id.btn_back);
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

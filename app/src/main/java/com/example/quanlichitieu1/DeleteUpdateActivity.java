package com.example.quanlichitieu1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.data.DBManager;
import com.example.quanlichitieu1.model.ChiTieu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DeleteUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePicker datePicker;
    private EditText edtTime,edtTien;
    private Spinner spnHangMuc;
    private Button btnSave,btnDelete;
    private ListView lvResult;

    private ContactAdapter contactAdapter;
    private List<ChiTieu> chiTieuList;
    public final DBManager dbManager = new DBManager(this);

    public int ViTriHangMuc = 0;
    public String HangMuc = "An Uong";
    private int Id;
    public static final String ID ="id";
    public static final String TIEN ="tien";
    public static final String VI_TRI_HANG_MUC ="vi tri hang muc";
    public static final String HANG_MUC ="hang muc";
    public static final String TIME ="time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);
        Widget();
        initSpinerHangMuc();
        initDatePicker();
        getData();

    }

    public void Widget(){
        datePicker = findViewById(R.id.date_picker);
        edtTime = findViewById(R.id.edt_time);
        edtTien = findViewById(R.id.edt_tien);
        edtTien.setText("0");


        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);

        spnHangMuc = findViewById(R.id.spn_hang_muc);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                ChiTieu chitieu = new ChiTieu();
                chitieu.setmID(Id);
                chitieu.setmTime(edtTime.getText().toString());
                chitieu.setmHangMuc(HangMuc);
                chitieu.setmViTriHangMuc(ViTriHangMuc);
                chitieu.setmTien(edtTien.getText().toString());
                dbManager.updateChiTieu(chitieu);
                Intent intent = new Intent(DeleteUpdateActivity.this,ChiTieuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_to_up,R.anim.anim_exit_to_up);
                break;
            case R.id.btn_delete:
                dbManager.deleteChiTieu(Id);
                Intent intent1 = new Intent(DeleteUpdateActivity.this,ChiTieuActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_up,R.anim.anim_exit_to_up);
                break;

        }

    }

    public void initSpinerHangMuc(){
        List<String> list = new ArrayList<>();
        list.add("An Uong");
        list.add("Giai Tri");
        list.add("The Thao");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnHangMuc.setAdapter(adapter);

        spnHangMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViTriHangMuc = position;
                HangMuc = spnHangMuc.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initDatePicker(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtTime.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);

            }
        });
    }


    private ChiTieu createChiTieu(){
        String tien = edtTien.getText().toString();
        String time = edtTime.getText().toString();
        if(Integer.parseInt(edtTien.getText().toString())==0){
            return null;
        }

        ChiTieu chitieu = new ChiTieu(tien,HangMuc,time,ViTriHangMuc);
        return chitieu;
    }
    public void getData(){
        Intent intent = getIntent();
        edtTien.setText(intent.getStringExtra(TIEN));
        edtTime.setText(intent.getStringExtra(TIME));
        Id = intent.getIntExtra(ID,0);
        spnHangMuc.setSelection(intent.getIntExtra(VI_TRI_HANG_MUC,0));
        HangMuc = intent.getStringExtra(HANG_MUC);
        ViTriHangMuc = intent.getIntExtra(VI_TRI_HANG_MUC,0);
    }


}

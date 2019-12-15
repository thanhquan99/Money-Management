package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.data.DBManager;
import com.example.quanlichitieu1.model.ChiTieu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePicker datePicker;
    private EditText edtTime,edtTien;
    private Spinner spnHangMuc;
    private Button btnSave;
    private ListView lvResult;
    ImageButton imgViTien,imgAdd,imgChiTieu;

    private ContactAdapter contactAdapter;
    private List<ChiTieu> chiTieuList;
    public final DBManager dbManager = new DBManager(this);

    public int ViTriHangMuc = 0;
    public String HangMuc = "An Uong";
    public static final String TIEN ="tien";
    public static final String VI_TRI_HANG_MUC ="vi tri hang muc";
    public static final String HANG_MUC ="hang muc";
    public static final String TIME ="time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Widget();
        initSpinerHangMuc();
        initDatePicker();

    }

    public void Widget(){
        datePicker = findViewById(R.id.date_picker);
        edtTime = findViewById(R.id.edt_time);
        edtTien = findViewById(R.id.edt_tien);
        edtTien.setText("0");

        imgViTien = findViewById(R.id.img_btn_vi_tien);
        imgViTien.setOnClickListener(this);
        imgAdd = findViewById(R.id.img_btn_add);
        imgAdd.setOnClickListener(this);
        imgChiTieu = findViewById(R.id.img_btn_chi_tieu);
        imgChiTieu.setOnClickListener(this);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        spnHangMuc = findViewById(R.id.spn_hang_muc);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_chi_tieu:
                Intent intent1 = new Intent(AddActivity.this,OptionActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_right,R.anim.anim_exit_to_right);
                break;
            case R.id.img_btn_add:
                break;
            case R.id.img_btn_vi_tien:
                Intent intent2 = new Intent(AddActivity.this,ViTienActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.anim_enter_to_left,R.anim.anim_exit_to_left);
                break;
            case R.id.btn_save:
                ChiTieu chitieu = createChiTieu();

                if(chitieu!=null){
                    dbManager.addChiTieu(chitieu);
                    Toast.makeText(AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Nhap tien vao di ban oi", Toast.LENGTH_SHORT).show();
                }
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
        edtTime.setText(day+"-"+(month+1)+"-"+year);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtTime.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);

            }
        });
    }


    private ChiTieu createChiTieu(){
        if( TextUtils.isEmpty(edtTien.getText().toString())){
            return null;
        }
        if(Integer.parseInt(edtTien.getText().toString())<=0 ){
            return null;
        }
        String tien = edtTien.getText().toString();
        String time = edtTime.getText().toString();

        ChiTieu chitieu = new ChiTieu(tien,HangMuc,time,ViTriHangMuc);
        return chitieu;
    }


}

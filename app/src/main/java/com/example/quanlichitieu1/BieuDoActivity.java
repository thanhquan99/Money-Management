package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlichitieu1.data.DBManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BieuDoActivity extends AppCompatActivity {
    Button btnBack;
    public float rainfall[]={50f,20f,30f};
    String chiteu[]={"Đồ ăn","Thể thao","Giải trí"};
    public final DBManager dbManager = new DBManager(this);
    float AnUong,TheThao,GiaiTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieu_do);

        GUI();
        getData();
        setupPieChart();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BieuDoActivity.this,OptionActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_up,R.anim.anim_exit_to_up);
            }
        });
    }

    private void setupPieChart() {
        List<PieEntry> pieEntries=new ArrayList<>();
        for (int i=0;i<rainfall.length;i++){
            pieEntries.add(new PieEntry(rainfall[i],chiteu[i]));
        }
        PieDataSet dataSet=new PieDataSet(pieEntries,"Biểu đồ chi tiêu");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);

        PieChart chart=(PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }

    public void GUI(){

        btnBack = findViewById(R.id.btn_back);
    }
    public void getData(){
        AnUong = dbManager.getAnUong();
        TheThao = dbManager.getTheThao();
        GiaiTri = dbManager.getGiaiTri();
        rainfall[0]=AnUong;
        rainfall[1]=TheThao;
        rainfall[2]=GiaiTri;
    }

}

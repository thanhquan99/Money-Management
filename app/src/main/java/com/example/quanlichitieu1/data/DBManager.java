package com.example.quanlichitieu1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlichitieu1.model.ChiTieu;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Chi Tieu Manager";
    private static final String TABLE_NAME = "Chitieu";
    private static final String ID = "Id";
    private static final String TIEN = "Tien";
    private static final String HANGMUC = "Hangmuc";
    private static final String TIME = "Time";
    private static final String VITRIHANGMUC = "Vitrihangmuc";
    private static int VERSION = 1;
    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            TIEN + " TEXT, " +
            HANGMUC + " TEXT, " +
            VITRIHANGMUC + " TEXT, " +
            TIME + " TEXT)";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addChiTieu(ChiTieu chitieu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIEN,chitieu.getmTien());
        values.put(HANGMUC,chitieu.getmHangMuc());
        values.put(VITRIHANGMUC,chitieu.getmViTriHangMuc());
        values.put(TIME,chitieu.getmTime());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public List<ChiTieu> getAllChiTieu(){
        List<ChiTieu> chiTieuList = new ArrayList<>();
        String selectQuery = "Select * From "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                ChiTieu chitieu = new ChiTieu();
                chitieu.setmID(cursor.getInt(0));
                chitieu.setmTien(cursor.getString(1));
                chitieu.setmHangMuc(cursor.getString(2));
                chitieu.setmViTriHangMuc(cursor.getInt(3));
                chitieu.setmTime(cursor.getString(4));
                chiTieuList.add(chitieu);

            }while(cursor.moveToNext());
        }
        db.close();
        return chiTieuList;
    }
    public void updateChiTieu(ChiTieu chitieu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEN,chitieu.getmTien());
        contentValues.put(HANGMUC,chitieu.getmHangMuc());
        contentValues.put(VITRIHANGMUC,chitieu.getmViTriHangMuc());
        contentValues.put(TIME,chitieu.getmTime());
        db.update(TABLE_NAME,contentValues,"ID = "+chitieu.getmID(),null);
    }
    public void deleteChiTieu(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID = "+ID,null);
    }
    public int getTongChiTieu() {
        String selectQuery = "Select " + TIEN +" From "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int tien = 0;
        if(cursor.moveToFirst()){
            do{
                tien+=cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        db.close();
        return tien;
    }
    public int getAnUong(){
        String selectQuery = "Select " + TIEN +" From "+TABLE_NAME+" Where "+VITRIHANGMUC+" = 0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int tien = 0;
        if(cursor.moveToFirst()){
            do{
                tien+=cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        db.close();
        return tien;
    }
    public int getTheThao(){
        String selectQuery = "Select " + TIEN +" From "+TABLE_NAME+" Where "+VITRIHANGMUC+" = 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int tien = 0;
        if(cursor.moveToFirst()){
            do{
                tien+=cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        db.close();
        return tien;
    }
    public int getGiaiTri(){
        String selectQuery = "Select " + TIEN +" From "+TABLE_NAME+" Where "+VITRIHANGMUC+" = 2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int tien = 0;
        if(cursor.moveToFirst()){
            do{
                tien+=cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        db.close();
        return tien;
    }
    public List<ChiTieu> takeAnUong(){
        List<ChiTieu> chiTieuList = new ArrayList<>();
        String selectQuery = "Select * From "+TABLE_NAME+ " Where "+VITRIHANGMUC+" =0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                ChiTieu chitieu = new ChiTieu();
                chitieu.setmID(cursor.getInt(0));
                chitieu.setmTien(cursor.getString(1));
                chitieu.setmHangMuc(cursor.getString(2));
                chitieu.setmViTriHangMuc(cursor.getInt(3));
                chitieu.setmTime(cursor.getString(4));
                chiTieuList.add(chitieu);

            }while(cursor.moveToNext());
        }
        db.close();
        return chiTieuList;
    }
    public List<ChiTieu> takeTheThao(){
        List<ChiTieu> chiTieuList = new ArrayList<>();
        String selectQuery = "Select * From "+TABLE_NAME+ " Where "+VITRIHANGMUC+" =2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                ChiTieu chitieu = new ChiTieu();
                chitieu.setmID(cursor.getInt(0));
                chitieu.setmTien(cursor.getString(1));
                chitieu.setmHangMuc(cursor.getString(2));
                chitieu.setmViTriHangMuc(cursor.getInt(3));
                chitieu.setmTime(cursor.getString(4));
                chiTieuList.add(chitieu);

            }while(cursor.moveToNext());
        }
        db.close();
        return chiTieuList;
    }
    public List<ChiTieu> takeGiaiTri(){
        List<ChiTieu> chiTieuList = new ArrayList<>();
        String selectQuery = "Select * From "+TABLE_NAME+ " Where "+VITRIHANGMUC+" =1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                ChiTieu chitieu = new ChiTieu();
                chitieu.setmID(cursor.getInt(0));
                chitieu.setmTien(cursor.getString(1));
                chitieu.setmHangMuc(cursor.getString(2));
                chitieu.setmViTriHangMuc(cursor.getInt(3));
                chitieu.setmTime(cursor.getString(4));
                chiTieuList.add(chitieu);

            }while(cursor.moveToNext());
        }
        db.close();
        return chiTieuList;
    }


}
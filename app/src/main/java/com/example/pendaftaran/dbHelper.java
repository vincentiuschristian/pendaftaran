package com.example.pendaftaran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    public static final String DB_name = "input.db";
    public static final String TABLE_NAME = "Pendaftaran";
    public static final String key_nama = "Nama";
    public static final String key_alamat = "Alamat";
    public static final String key_nohp = "NoHp";
    public static final String key_jeniskelamin = "JenisKelamin";


    public dbHelper(Context context) {

        super(context, "input.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Pendaftaran( Nama TEXT Primary Key , Alamat Text, NoHP Number, JenisKelamin String)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Pendaftaran");
    }

    public Boolean insertpendaftaran(String key_nama, String key_alamat, String key_nohp) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nama", key_nama);
        contentValues.put("Alamat", key_alamat);
        contentValues.put("NoHP", key_nohp);
        long result = DB.insert("input", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Pendaftaran", null);
        return cursor;
    }




}
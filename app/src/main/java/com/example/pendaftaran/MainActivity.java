package com.example.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText nama, nohp ,alamat;
    Button button;
    dbHelper DB;

    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.editTextTextPersonName5);
        alamat = findViewById(R.id.editTextTextPersonName2);
        nohp = findViewById(R.id.editTextTextPersonName3);

        button  = findViewById(R.id.button3);
        DB = new dbHelper(this);

        Button uplod = findViewById(R.id.btn_uplod);
        imageView = findViewById(R.id.image);

        uplod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Esixt",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer =new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("nama :"+res.getString(0)+"\n");
                    buffer.append("alamat :"+res.getString(1)+"\n");
                    buffer.append("nohp :"+res.getString(2)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            String namaTXT=nama.getText().toString();
            String alamatTXT=alamat.getText().toString();
            String nohpTXT=nohp.getText().toString();

            Boolean checkinsertdata = DB.insertpendaftaran(namaTXT,alamatTXT,nohpTXT);
                if ( checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Insertes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Insertes", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }



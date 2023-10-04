package com.example.pet2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemThuCung extends AppCompatActivity {
    EditText edtTenTT,edtGiongLoai,edtTrongLuong,edtMauLong;
    Button btnthem,btnhuy;
    ImageButton ibtnCamera,ibtnFolder;
    ImageView imgHinh;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thu_cung);
        Mapping();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemThuCung.this,MainActivity.class));
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyen data cua imageview sang byte[]
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh=byteArray.toByteArray();


                MainActivity.database.INSERT_THUCUNG(
                        edtTenTT.getText().toString().trim(),
                        edtGiongLoai.getText().toString().trim(),
                        edtTrongLuong.getText().toString().trim(),
                        edtMauLong.getText().toString().trim(),
                        hinhAnh

                );
                Toast.makeText(ThemThuCung.this,"ĐÃ THÊM",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemThuCung.this,MainActivity.class));
            }
        });
        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data !=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void Mapping() {
        btnthem=findViewById(R.id.btnThem);
        btnhuy=findViewById(R.id.btnHuy);
        ibtnCamera=findViewById(R.id.ibtnCamera);
        ibtnFolder=findViewById(R.id.ibtnFolder);
        edtTenTT=findViewById(R.id.edtTenTT);
        edtGiongLoai=findViewById(R.id.edtGiongLoai);
        edtTrongLuong=findViewById(R.id.edtTrongLuong);
        edtMauLong=findViewById(R.id.edtMauLong);
        imgHinh=findViewById(R.id.imgHinh);
    }

}

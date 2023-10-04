package com.example.pet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnthem;
    ListView lvThuCung;
    ArrayList<ThuCung> thuCungArrayList;
    ThuCungAdapter adapter;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnthem = findViewById(R.id.btnthem);
        lvThuCung = findViewById(R.id.lvThuCung);

        thuCungArrayList = new ArrayList<>();
        adapter = new ThuCungAdapter(this, R.layout.thu_cung, thuCungArrayList);
        lvThuCung.setAdapter(adapter);

        database = new Database(this,"QLThuCung.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS ThuCung(ID INTEGER PRIMARY KEY AUTOINCREMENT,TenTT VARCHAR(50),GiongLoai VACHAR(50),TrongLuong VACHAR(50),MauLong VACHAR(50), HinhAnh BLOB)");

        Cursor cursor = database.GetData("SELECT * FROM ThuCung");
        while (cursor.moveToNext()){
            thuCungArrayList.add(new ThuCung(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5)
            ));
        }
        adapter.notifyDataSetChanged();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ThemThuCung.class));
            }
        });
    }
}
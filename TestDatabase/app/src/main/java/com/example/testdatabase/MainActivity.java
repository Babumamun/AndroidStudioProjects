package com.example.testdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button btn_insert,btn_delete,btn_update;
EditText et_id,et_name,ed_address,ed_phone,et_email;
SQLiteOpenHelper OpenHelper;
SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inti();
        OpenHelper=new databasehelper(this);
        btn_insert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // String name=

    }
});




    }

    public void inti(){
        btn_insert=findViewById(R.id.btn_insert);
        btn_delete=findViewById(R.id.btn_delete);
        btn_update=findViewById(R.id.btn_update);
        et_id=findViewById(R.id.et_id);
        et_name=findViewById(R.id.et_name);
        ed_address=findViewById(R.id.ed_address);
        ed_phone=findViewById(R.id.ed_phone);
        et_email=findViewById(R.id.et_email);
    }
}
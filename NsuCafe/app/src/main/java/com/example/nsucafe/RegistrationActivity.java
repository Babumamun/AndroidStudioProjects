package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText ed_name;
    private  EditText ed_email;
    private  EditText ed_pass;
    private EditText ed_conpass;
    private TextView tv_haveAcc;
    private Button btn_regisitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ed_name=findViewById(R.id.et_name);
        ed_email= findViewById(R.id.ed_email_phn);
        ed_pass= findViewById(R.id.pass);
        ed_conpass=findViewById(R.id.con_pass);
        tv_haveAcc=findViewById(R.id.have_account);
        btn_regisitar=findViewById(R.id.btn_register);
        btn_regisitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(RegistrationActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        tv_haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(RegistrationActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}
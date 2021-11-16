package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import Utils.SPUtils;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private TextView tv_signin;
    private TextView tv_email;
    private TextView tv_password;
    private CheckBox Cb_Remember_me;
    private CheckBox cb_Auto_login;
    private String sUserName;
    private String sPwd;
    private boolean sRemState;  //Save Remember me
    private boolean sAutoState;  // Save Auto login


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (sAutoState) {
            cb_Auto_login.setChecked(sAutoState);
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            // intent.putExtra("user", email);
            tv_email.setText(sUserName);
            tv_password.setText(sPwd);
            startActivity(intent);

        } else if (sRemState) {
            Cb_Remember_me.setChecked(sRemState);
            tv_email.setText(sUserName);
            tv_password.setText(sPwd);

        }
    }

    private void init() {
        tv_email = findViewById(R.id.tv_email);
        tv_password = findViewById(R.id.tv_password);
        Cb_Remember_me = findViewById(R.id.cb_save);
        cb_Auto_login = findViewById(R.id.cb_atuo_login);
        btn = findViewById(R.id.login);
        tv_signin = findViewById(R.id.sing_in);
        btn.setOnClickListener(this);
        tv_signin.setOnClickListener(this);
        getSavedInfo();
    }

    private void getSavedInfo() {
        Map<String, String> map = SPUtils.getUser(this);
        if (map != null) {
            sUserName = map.get("login");
            sPwd = map.get("pwd");

        }
        Map<String, Boolean> reMap = SPUtils.getRemMe(this);
        if (reMap != null)
            sRemState = reMap.get("Remember_me");

        Map<String, Boolean> autoMap = SPUtils.getAuto(this);
        if (autoMap != null)
            sAutoState = autoMap.get("Auto_login");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                sing_up();
                break;
            case R.id.sing_in:
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void savedState() {
        if (Cb_Remember_me.isChecked()) {
            SPUtils.saveRemMe(this, true);
        } else {
            SPUtils.saveRemMe(this, false);
        }
        if (cb_Auto_login.isChecked()) {
            SPUtils.saveAuto(this, true);
        } else {
            SPUtils.saveAuto(this, false);
        }
    }

    private void sing_up() {
        // get input
        String email = tv_email.getText().toString().trim();
        String password = tv_password.getText().toString().trim();
        // check user input
//        if (isValidName(email) & isValidPassword(password)) {
//            if (SPUtils.saveUser(this, email, password)) {
//
//                Toast.makeText(this, "Save data Successful", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Save data Fail", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "User Name and password can not be empty", Toast.LENGTH_SHORT).show();
//        }
        // compare with user name and password
        if (email.equals(sUserName) & password.equals(sPwd)) {
            savedState();

            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            intent.putExtra("user", email);
            startActivity(intent);
        } else {
            Toast.makeText(this, "User Name or password is not Correct", Toast.LENGTH_SHORT).show();
        }

        //savedState();
    }
}


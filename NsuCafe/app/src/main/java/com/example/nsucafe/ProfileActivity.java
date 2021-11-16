package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private RelativeLayout title_bar;
    private TextView tv_shop_name,tv_title,tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        title_bar=findViewById(R.id.title_bar);
        tv_title=findViewById(R.id.tv_title);
        tv_back=findViewById(R.id.tv_back);
        tv_title.setText("我");
        tv_title.setTextColor(getResources().getColor(R.color.blue_color));
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
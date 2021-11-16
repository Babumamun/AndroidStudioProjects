package com.example.nsucafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Adapters.FoodAdapter;
import Model.FoodBean;

public class FoodActivity extends AppCompatActivity {
    private RelativeLayout title_bar;
    private TextView tv_title,tv_back;
    private TextView tv_tase;
    private TextView tv_sal_num;
    private TextView tv_price;
    private ImageView iv_food;
    private FoodBean bean;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

    bean= (FoodBean) getIntent().getSerializableExtra("food");
        initView();
        setData();

    }

    private void setData() {
    tv_sal_num.setText("Sale Number:"+bean.getSaleNum());
    tv_tase.setText(bean.getTaste());
    tv_price.setText(bean.getPrice()+"¥");
        Glide.with(this)
                .load(bean.getFoodPic())
                .error(R.mipmap.ic_launcher)
                .into(iv_food);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        tv_price=findViewById(R.id.tv_price2);
        tv_tase=findViewById(R.id.tv_tast);
        tv_sal_num=findViewById(R.id.tv_sal_num);
        iv_food=findViewById(R.id.iv_food_pic);
        title_bar=findViewById(R.id.title_bar);
        tv_title=findViewById(R.id.tv_title);
        tv_back=findViewById(R.id.tv_back);
        tv_title.setText("Food Detail");
        tv_title.setTextColor(getResources().getColor(R.color.blue_color));
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_food);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Food");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
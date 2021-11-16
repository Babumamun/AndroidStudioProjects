package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import Adapters.OrderAdapter;
import Model.FoodBean;
import Model.ShopBean;

public class OrderActivity extends AppCompatActivity {
    private TextView tv_cost,tv_total_cost,tv_payment;
    private RelativeLayout rl_title_bar;
    private BigDecimal totalCost;
    private int totalItems;
    private List<FoodBean>carFoodList;
    private ListView lv_order;
    private OrderAdapter adapter;
    private ShopBean bean;
    private ListView list_order_items;
    private RelativeLayout title_bar;
    private TextView tv_title,tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

//        bean = (ShopBean) getIntent().getSerializableExtra("food");
//        if (bean == null)
//            return;

        Intent intent= getIntent();

       carFoodList= (List<FoodBean>) intent.getSerializableExtra("carFoodList");
        totalItems=intent.getIntExtra("totalItems",0);
        totalCost= (BigDecimal) intent.getSerializableExtra("totalCost");


        initView();
        setData();
        setTitleBar();
    }

    private void setTitleBar() {
        title_bar=findViewById(R.id.title_bar);
        tv_title=findViewById(R.id.tv_title);
        tv_back=findViewById(R.id.tv_back);
        tv_title.setText("Order Details");
        tv_title.setTextColor(getResources().getColor(R.color.account_color));
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setData() {
  adapter=new OrderAdapter(this);
    adapter.setData(carFoodList);
   tv_cost.setText(""+totalItems);
   tv_total_cost.setText("¥"+totalCost);
        lv_order.setAdapter(adapter);
        
    }

    private void initView() {
        lv_order=findViewById(R.id.lv_order);
        tv_total_cost=findViewById(R.id.tv_total_cost);
        tv_cost=findViewById(R.id.tv_cost);
        tv_payment=findViewById(R.id.tv_payment);
        //list_order_items=findViewById(R.id.list_order_items);
        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(OrderActivity.this,R.style.Dialog_Style);
                dialog.setContentView(R.layout.qr_code);
                dialog.show();
            }
        });


    }
    
    
}
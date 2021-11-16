package com.example.nsucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import Adapters.CartAdapter;
import Adapters.MenuAdapter;
import Model.Card;
import Model.FoodBean;
import Model.ShopBean;

public class ShopDetailActivity extends AppCompatActivity implements
        View.OnClickListener {
    private RelativeLayout title_bar;
    private TextView tv_shop_name,tv_title,tv_back;
    private TextView tv_time;
    private TextView tv_notice;
    private ImageView iv_shop;
    private ListView listView;
    private ShopBean bean;
    private MenuAdapter adapter;
    private CartAdapter cartAdapter;
    private Card card;
    private List<FoodBean>fbl;
    private TextView tv_cost;
    private TextView tv_count;
    private RelativeLayout rl_shop_cart;
    private RelativeLayout rl_cart_list;
    private ListView lv_cart_list;
    private ImageView btn_order;
    private ImageView iv_shop_cart;
    private TextView tv_clear;
    //private FoodAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        bean = (ShopBean) getIntent().getSerializableExtra("shop");
        if (bean == null)
            return;

        intView();
        intAdapter();
        setData();

    }

    private void intAdapter() {
            card=new Card();
        adapter= new MenuAdapter(this, new MenuAdapter.OnSelectListener() {
            @Override
            public void onSelectAddCard(int position) {
                FoodBean fb= fbl.get(position);
                fb.setCount(1);
                if(card==null){
                    card = new Card();
                }
                card.addToCard(fb);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_count.setText(card.getTotalCount()+"");
                        tv_cost.setText(card.getTotalCost()+"¥");
                    }
                });
            }
        });
        cartAdapter= new CartAdapter(this, new CartAdapter.OnCountListener() {
            @Override
            public void onChange() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_count.setText(card.getTotalCount()+"");
                        tv_cost.setText(card.getTotalCost()+"");
                    }
                });

            }
        });

        //adapter = new FoodAdapter(this);
        listView.setAdapter(adapter);

    }


    private void intView() {
        title_bar=findViewById(R.id.title_bar);
        tv_title=findViewById(R.id.tv_title);
        tv_back=findViewById(R.id.tv_back);
        tv_title.setText("Shop Items");
        tv_title.setTextColor(getResources().getColor(R.color.blue_color));
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setOnClickListener(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Shop Details");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_shop_name = findViewById(R.id.tv_shop_name);
        tv_time = findViewById(R.id.tv_time);
        //tv_notice = findViewById(R.id.tv_notice);
        iv_shop = findViewById(R.id.iv_shop);
        listView = findViewById(R.id.list_view_food);
        tv_count=findViewById(R.id.tv_items);
        tv_cost=findViewById(R.id.tv_cost);
        rl_shop_cart=findViewById(R.id.rl_shop_cart);
       rl_cart_list=findViewById(R.id.rl_cart_list);
       lv_cart_list=findViewById(R.id.lv_cart_list);
        iv_shop_cart=findViewById(R.id.iv_shop_cart);
       btn_order=findViewById(R.id.btn_order);
        tv_clear=findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);
       btn_order.setOnClickListener(this);
       iv_shop_cart.setOnClickListener(this);
       rl_cart_list.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               if (rl_cart_list.getVisibility()==View.VISIBLE){
                   rl_cart_list.setVisibility(View.GONE);
               }

               return false;
           }
       });


    }

    private void setData() {
        tv_shop_name.setText(bean.getShopName());
        tv_time.setText(bean.getTime());
        //tv_notice.setText(bean.getShopNotice());

        Glide.with(this)
                .load(bean.getShopPic())
                .error(R.mipmap.ic_launcher)
                .into(iv_shop);
        fbl=bean.getFoodList();
     adapter.setData(fbl);
        //adapter.setData(bean.getFoodList());

    }

    @Override
    public void onClick(View v) {
   switch (v.getId()) {
       case R.id.btn_order:
           if (card.getTotalCount()>0){
        Intent intent= new Intent(ShopDetailActivity.this, OrderActivity.class);
        intent.putExtra("carFoodList", (Serializable) card.getCard());
        intent.putExtra("totalItems",card.getTotalCount());
        intent.putExtra("totalCost",(Serializable) card.getTotalCost());
        startActivity(intent);
           }
           break;

       case R.id.iv_shop_cart:
           if (card.getTotalCount()<= 0) {
               return;
           }
           if (rl_cart_list != null) {
               if (rl_cart_list.getVisibility() == View.VISIBLE) {
                   rl_cart_list.setVisibility(View.GONE);
               } else {
                   rl_cart_list.setVisibility(View.VISIBLE);
                   Animation animation = AnimationUtils.loadAnimation(
                           ShopDetailActivity.this, R.anim.cart_animation);
                   rl_cart_list.startAnimation(animation);
               }
           }
           cartAdapter.setData(card.getCard());
           lv_cart_list.setAdapter(cartAdapter);
           break;
       case R.id.tv_back:
           finish();
           break;

       case R.id.tv_clear:
           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShopDetailActivity.this, R.style.MyDialogTheme);
           //AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ShopDetailActivity.this, R.style.MyDialogTheme);
            alertDialogBuilder.setTitle(Html.fromHtml("<font color='#FF7F27'>Are you sure!You wanted to Clear cart?</font>"));
//           alertDialogBuilder.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Yes</font>"),
           //alertDialogBuilder.setMessage("Are you sure,"+"You wanted to Clear cart");
                   alertDialogBuilder.setPositiveButton("Yes",
                           new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface arg0, int arg1) {
                                  card=new Card();
                                  refreshSum();
                                  cartAdapter.setData(card.getCard());
                                  lv_cart_list.setAdapter(cartAdapter);
                               }

                           });

           alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
               @ Override
               public void onClick(DialogInterface dialog, int which) {
                    return;
                  // finish();
               }
           });

           AlertDialog alertDialog = alertDialogBuilder.create();
           alertDialog.show();
          // alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.background_dark);
           break;
   }

   }

   private void refreshSum(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_count.setText(card.getTotalCount()+"");
                tv_cost.setText(card.getTotalCost()+"");
            }
        });
   }


    }


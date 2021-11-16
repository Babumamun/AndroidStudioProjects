package com.example.nsucafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Adapters.FoodRecyclerAdapter;
import Adapters.PsAdapter;
import Adapters.ShopAdapter;
import Model.Card;
import Model.ShopBean;
import Utils.Constant;
import Utils.JsonParse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//implementation 'com.squareup.picasso:picasso:2.71828'

public class ShopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout title_bar;
    private TextView tv_shop_name,tv_title,tv_back;
    private ListView list_View,lv_popular;
    private ImageView iv;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private BottomAppBar bottomAppBar_menu;
    private FloatingActionButton floatingActionButton;
    private NavigationView navigationView;

    ShopAdapter shopAdapter = new ShopAdapter(this);
//    PsAdapter psAdapter= new PsAdapter(this);
       FoodRecyclerAdapter foodRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_shop);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Home");

        initView();
        initData();
        initDrawer();


    }
    private void initDrawer() {
//        bottomAppBar_menu.setOnMenuItemClickListener(menui);
//        bottomAppBar_menu.setOnMenuItemClickListener { menuItem ->
//                when (menuItem.itemId) {
//            R.id.search -> {
//                // Handle search icon press
//                true
//            }
//            R.id.more -> {
//                // Handle more item (inside overflow menu) press
//                true;
//            }
//        else  false;
//        }
//        }
        //setSupportActionBar(bottomAppBar_menu);
        bottomAppBar_menu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the navigation click by showing a BottomDrawer etc.
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

       bottomAppBar_menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle actions based on the menu item
                int id=item.getItemId();
               switch (id){
                   case R.id.account:
                       Intent intent= new Intent(ShopActivity.this,ProfileActivity.class);
                       startActivity(intent);

                break;
               }
                return true;
            }
        });

//        bottomAppBar_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(ShopActivity.this,R.style.Dialog_new_Style);
                dialog.setContentView(R.layout.notification);
                dialog.show();
            }
        });
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        String url = Constant.WEB_SITE + Constant.REQUEST_SHOP_DATA;
         Log.i("*****************",url);
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                  Log.i("***********************", "Success"+json);
                final List<ShopBean> sbl = JsonParse.getInstance().getShopList(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        shopAdapter.setData(sbl);
//                        psAdapter.setData(sbl);
                        recyclerView.setAdapter(new FoodRecyclerAdapter(ShopActivity.this,sbl));
                    }
                });

            }
        });
    }
    private void initView() {
        iv = findViewById(R.id.imageView);
            list_View = findViewById(R.id.list_sop);
            list_View.setAdapter(shopAdapter);
            recyclerView=findViewById(R.id.popular_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager
                    (this,RecyclerView.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);
            drawerLayout=findViewById(R.id.drawer_layout);
            navigationView=findViewById(R.id.navigator_view);
        bottomAppBar_menu=findViewById(R.id.bottom_menu);
        floatingActionButton=findViewById(R.id.floating_notification);
//            lv_popular=findViewById(R.id.lv_popular);
//           lv_popular.setAdapter(psAdapter);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent intent= new Intent(ShopActivity.this,ShopActivity.class);
               startActivity(intent);
                break;
            case R.id.nav_cycle:
                Dialog dialog= new Dialog(ShopActivity.this,R.style.Dialog_new_Style);
                dialog.setContentView(R.layout.vip_check);
                dialog.show();
                break;
//            case R.id.nav_bus:
//                Toast.makeText(getApplicationContext(),"home2",
//                        Toast.LENGTH_SHORT).show();
//                break;
            case R.id.nav_login:
               // Intent intent1= new Intent(this,MainActivity.class);
              //  startActivity(intent1);
                this.finish();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShopActivity.this, R.style.MyDialogTheme);
                alertDialogBuilder.setTitle(Html.fromHtml("<font color='#FF7F27'>Are you sure!You want to log out?</font>"));
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //                Intent logout=new Intent(getApplicationContext(),MainActivity.class);
//                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(logout);
//                finish();
//                Toast.makeText(getApplicationContext(),"home4",
//                        Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.nav_profile:
                Intent intent1= new Intent(ShopActivity.this,ProfileActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_share:
                Toast.makeText(getApplicationContext(),"Check Your Internet Connection",
                      Toast.LENGTH_SHORT).show();
                break;
        }


        return false;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.bottom_menu,menu);
//        return true;
//
//        //return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//    int id=item.getItemId();
//    if (id==R.id.nav_home){
//        Intent intent= new Intent(ShopActivity.this,ShopActivity.class);
//        startActivity(intent);
//
//    }
//     return true;
//        //return super.onOptionsItemSelected(item);
//    }
//
}
        //  aosb kicu na
    //
    //        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json=response.body().string();
//              //  Log.i("*****************",json);
//            List<ShopBean>sbl= JsonParse.getInstance().getShopList(json);
//            shopAdapter.setData(sbl);
//            }
//        });
//        List<Shop> shopList=new ArrayList<>();
//        for (int i=0;i<20;i++)
//            shopList.add(new Shop("Shop"+i,"Shop"+i+"Desc",R.drawable.kfc));
//        shopAdapter.setData(shopList);
 //91   }






//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//92}
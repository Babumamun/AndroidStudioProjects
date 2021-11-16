package com.example.nsufood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import Adapters.ShopAdapter;
import Model.ShopBean;
import Utils.Constant;
import Utils.JsonParse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity {
    private ListView listView;
    private ImageView iv;

    ShopAdapter shopAdapter= new ShopAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();

    }

    private void initView() {
        iv = findViewById(R.id.image_View);
        listView = findViewById(R.id.list_item);
        listView.setAdapter(shopAdapter);
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        String url = Constant.WEB_SITE + Constant.REQUEST_SHOP_DATA;
     //  Log.i("*****************",url);
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
              //    Log.i("***********************", "Success"+json);
                final List<ShopBean> sbl = JsonParse.getInstance().getShopList(json);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        shopAdapter.setData(sbl);
                    }
                });

            }
        });

    }
}
package com.example.nsubucks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import dbutils.DataBaseHelper;
import dbutils.DrinkDao;

public class AddProductActivity extends AppCompatActivity {
    private EditText editText_name;
    private EditText editText_des;
    private EditText editText_price;
    private TextView textView_name;
    private TextView textView_des;
    private TextView textView_price;
    private Button button_save;
    private ImageView imageView;
    final int REQUEST_CODE_GALLERY=999;
   public static DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    init();
    dataBaseHelper= new DataBaseHelper(this,"drinkdb.sqlite",null,1);
    dataBaseHelper.queryData("CREATE TABLE IF NOT EXISTS DRINK(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,des VARCHAR,price VARCHAR,image BLOG) ");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"pick an image"),REQUEST_CODE_GALLERY);
//                ActivityCompat.requestPermissions(AddProductActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY );
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dataBaseHelper.insertData(
                            editText_name.getText().toString().trim(),
                            editText_des.getText().toString().trim(),
                            editText_price.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );

                 Toast.makeText(getApplicationContext(),"Add successfully!",Toast.LENGTH_SHORT).show();
                 editText_name.setText("");
                 editText_des.setText("");
                 editText_price.setText("");
                 imageView.setImageResource(R.mipmap.ic_launcher);

                }catch (Exception e){
                    e.printStackTrace();

                }


//                    DrinkDao dd = new DrinkDao();
//                    Drink drink = new Drink(1, editText_name.getText().toString().trim(), editText_des.getText().toString().trim(), Integer.parseInt(editText_price.getText().toString().trim()), (imageViewToByte(imageView)));
//                    dd.saveDrink(drink);

//                    Toast.makeText(getApplicationContext(),"data saved",Toast.LENGTH_SHORT).show()
//                    editText_name.setText("");
//                    editText_des.setText("");
//                    editText_price.setText("");
//                    imageView.setImageResource(R.mipmap.ic_launcher);

            }
        });

    }

   public static byte[] imageViewToByte(ImageView image) {
    Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray=stream.toByteArray();
    return bytearray;
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode==REQUEST_CODE_GALLERY){
//            if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                Intent intent= new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,REQUEST_CODE_GALLERY);
//
//
//            }else {
//                Toast.makeText(getApplicationContext(),"You don't have permission to open gallery",Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
//            try {
//                InputStream inputStream= getContentResolver().openInputStream(uri);
//                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
//                imageView.setImageBitmap(bitmap);
//
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

        }


      super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        textView_name=findViewById(R.id.tv_name);
        textView_des=findViewById(R.id.et_des);
        textView_price=findViewById(R.id.tv_price);
        editText_name=findViewById(R.id.et_name);
        editText_des=findViewById(R.id.et_des);
        editText_price=findViewById(R.id.et_price);
        button_save=findViewById(R.id.btn_save);
        imageView=findViewById(R.id.imag_view);
    }
}
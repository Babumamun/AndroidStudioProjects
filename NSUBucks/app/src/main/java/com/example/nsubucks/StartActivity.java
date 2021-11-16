package com.example.nsubucks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import Adapters.MyAdapter;
import dbutils.DataBaseHelper;

public class StartActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Drink> list;
    MyAdapter myAdapter = null;
    final int REQUEST_CODE_GALLERY = 888;
    DataBaseHelper dataBaseHelper;
    // DrinkDao drinkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_item);
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, R.layout.my_custom_view, list);
        listView.setAdapter(myAdapter);
        // get data from sqlite database
        try {
            Cursor cursor = AddProductActivity.dataBaseHelper.getData("SELECT *FROM DRINK");
            list.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String des = cursor.getString(2);
                String price = cursor.getString(3);
                byte[] image = cursor.getBlob(4);
                list.add(new Drink(id, name, des, price, image));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        myAdapter.notifyDataSetChanged();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()

        {
            @Override
            public boolean onItemLongClick (AdapterView < ? > parent, View view,int position, long id){
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(StartActivity.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            Toast.makeText(getApplicationContext(), "Update...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Delete...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;

            }
        });




//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                CharSequence[]items={"Update", "delete"};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(StartActivity.this);
//                dialog.setTitle("Choose an action");
//                dialog.setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int item) {
//                                if (item == 0) {
//                                    // update
//                                    Toast.makeText(getApplicationContext(),"Update...",Toast.LENGTH_SHORT).show();
////                                    Cursor c = AddProductActivity.dataBaseHelper.getData("SELECT id FROM DRINK");
////                                    ArrayList<Integer> arrID = new ArrayList<Integer>();
////                                    while (c.moveToNext()){
////                                        arrID.add(c.getInt(0));
//                                }
//                                // show dialog update at here
////                                    showDialogUpdate(StartActivity.this, arrID.get(position));
//
//
//                                else {
//                                    Toast.makeText(getApplicationContext(),"Delete...",Toast.LENGTH_SHORT).show();
//                                    // delete
////                                    Cursor c = AddProductActivity.dataBaseHelper.getData("SELECT id FROM DRINK");
////                                    ArrayList<Integer> arrID = new ArrayList<Integer>();
////                                    while (c.moveToNext()){
////                                        arrID.add(c.getInt(0));
////                                    }
////                                    showDialogDelete(arrID.get(position));
////                                }
//                                }
//                            }
//                });
//                dialog.show();
//                return true;
//            }
//        });
    }

    private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(StartActivity.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AddProductActivity.dataBaseHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.upadate_food_activity);
        dialog.setTitle("Update");

        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        final EditText eddsc = (EditText) dialog.findViewById(R.id.edt_desc);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        imageViewFood .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "pick an image"), REQUEST_CODE_GALLERY);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddProductActivity.dataBaseHelper.updateData(
                            edtName.getText().toString().trim(),
                            eddsc.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                           AddProductActivity.imageViewToByte(imageViewFood),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }
    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = AddProductActivity.dataBaseHelper.getData("SELECT * FROM DRINK");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String dsc=cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Drink(id,name, price,dsc, image));
        }
        myAdapter.notifyDataSetChanged();
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                imageViewFood .setImageURI(uri);
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



//    private void LoadDatainListView() {
//        DrinkDao drinkDao = new DrinkDao(this);
//        drinkList = drinkDao.getAllDrinks();
     // drinkList=drinkDao.getAllDrinks();
//        myAdapter=new MyAdapter(this,drinkList);
//        listView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_product:
                Intent intent = new Intent(this, AddProductActivity.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                return true;
//                Intent intent0 = new Intent(this, AddProductActivity.class);
//                startActivity(intent0);

            case R.id.modify:
                Intent intent8 = new Intent(this, AddProductActivity.class);
                startActivity(intent8);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Drink getSelectedItem = list.get(position);
//
//
//            }
//        });

        //        DrinkDao drinkDao = new DrinkDao(this);
//        List<Drink> drinkList = drinkDao.getAllDrinks();
//
//        ArrayAdapter drinkAdapter = new ArrayAdapter<Drink>(this, android.R.layout.simple_list_item_1, drinkList);
//        ListView listView= findViewById(R.id.list_item);
//        listView.setAdapter(drinkAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        Toast.makeText(getApplicationContext(), "You clicked first list", Toast.LENGTH_SHORT).show();
//                        //Toast toast=Toast. makeText(getApplicationContext(),"Hello Javatpoint",Toast. LENGTH_SHORT);
//                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(), "You clicked 2nd list", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(getApplicationContext(), "You clicked 3rd list", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 3:
//                        Toast.makeText(getApplicationContext(), "You clicked 4th list", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 4:
//                        Toast.makeText(getApplicationContext(), "You clicked 5th list", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });
//
//
////      for (Drink drink:drinkList){
////          Log.i("Drink",drink.toString());
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        DrinkDao dd = new DrinkDao(this);
//        Drink drink = new Drink(1, "Latte", "A couple of espresso shots with steamed milk", 10, R.drawable.latte);
//        dd.saveDrink(drink);
//        drink = new Drink(2, "Cappuccino", "Espresso, hot milk, and a steamed milk foam", 12, R.drawable.cappuccino);
//        dd.saveDrink(drink);
//        drink = new Drink(3, "Filter", "Highest quality beans roasted and brewed fresh", 15, R.drawable.filter);
//        dd.saveDrink(drink);
//        drink = new Drink(4, "Mango jose", "In blender, add ice, Jose Cuervo Especial® Silver", 20, R.drawable.mangojuice);
//        dd.saveDrink(drink);
//        drink = new Drink(5, "APPLE PIE", "An apple pie is a pie in which the principal filling ingredient is apple.", 25, R.drawable.apple);
//        dd.saveDrink(drink);
//    }
//
    }
}



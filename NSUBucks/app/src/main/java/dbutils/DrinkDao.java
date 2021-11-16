package dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.nsubucks.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkDao {
    Context context;
    DataBaseHelper dataBaseHelper;
    List<Drink> drinkList;

//    public DrinkDao(Context context) {
//        dataBaseHelper = new DataBaseHelper(context);
//    }

    public DrinkDao() {
        
    }


    public  void saveDrink(Drink drink) {
        SQLiteDatabase sq = dataBaseHelper.getWritableDatabase();
//        SQLiteStatement statement=sq.compileStatement(String.valueOf(drink));
//        statement.clearBindings();
//        statement.bindString(1,drink.getId());
//        statement.bindString(2,drink.getName());
//        statement.bindString(3,drink.getInformation());
//        statement.bindString(4,drink.getPrice());
//        statement.bindString(5,drink.getImgId());
//        statement.executeInsert();
        ContentValues cv = new ContentValues();
        cv.put("id", drink.getId());
        cv.put("name", drink.getName());
        cv.put("information", drink.getInformation());
        cv.put("price", drink.getPrice());
        cv.put("imgid", drink.getImgId());
        sq.insert("drink", null,cv);
        Log.i("saveDrink", "Successes");
        sq.close();

}
    public List<Drink> getAllDrinks() {
        List<Drink> drinkList = new ArrayList<>();

        SQLiteDatabase sq = dataBaseHelper.getReadableDatabase();
        Bitmap bt=null;
        String sql = "Select * from drink";
        Cursor cursor = sq.rawQuery(sql, null);
        // get each record  and wrap into the  drink object
//        if (cursor.moveToFirst())
//            do {
//                Drink drink = new Drink();
//                drink.setId(Integer.parseInt(cursor.getString(0)));
//                drink.setName(cursor.getString(1));
//                drink.setInformation(cursor.getString(2));
//                drink.setPrice(Integer.parseInt(cursor.getString(3)));
//                drink.setImgId(cursor.getBlob(4));
//                drinkList.add(drink);
//            } while (cursor.moveToNext());

//        while (cursor.moveToNext()){
//
//            int id= cursor.getInt(0);
//            String name=cursor.getString(1);
//            String dsc=cursor.getString(2);
//            int price=cursor.getInt(3);
//            byte [] image_view=cursor.getBlob(4);
//            Drink drink= new Drink(id,name,dsc,price,image_view);
//            drinkList.add(drink);
//        }
//
       return drinkList;
//
   }

}

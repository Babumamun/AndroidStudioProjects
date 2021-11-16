package dbutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.nsubucks.Drink;


public class DataBaseHelper extends SQLiteOpenHelper {
//    private static final String  DATABASE_NAME= "DrinkInf.db";
//    private static final int DATABASE_VERSION= 1;
//    private static final String TABLE_NAME="DrinkDetails";
//    private static final String TABLE_CONTACTS= "contacts";
//    private static final String KEY_NAME= "name";
//    private static final String KEY_INF= "information";
//    private static final int PRICE= 1;
//    private static final int IMAGE_ID= 1;

    public DataBaseHelper( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "contactsManager";
//    private static final String TABLE_CONTACTS = "contacts";
//    private static final String KEY_ID = "id";
//    private static final String KEY_NAME = "name";
//    private static final String KEY_PH_NO = "phone_number";

//    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
//            + KEY_PH_NO + " TEXT" + ")";
//        db.execSQL(CREATE_CONTACTS_TABLE);


//    public DataBaseHelper(Context context ) {
//        super(context,DATABASE_NAME , null, DATABASE_VERSION);
//    }

    public void queryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);


    }
    public void insertData(String name,String des,String price,byte[]image){
        SQLiteDatabase database= getWritableDatabase();
        String sql="INSERT INTO DRINK VALUES(Null,?,?,?,?) ";
        SQLiteStatement statement= database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,des);
        statement.bindString(3,price);
        statement.bindBlob(4,image);
        statement.executeInsert();
    }
   public Cursor getData(String sql){
       SQLiteDatabase database= getReadableDatabase();
       return database.rawQuery(sql,null);
   }
    public void updateData(String name,String des, String price, byte[] image,int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE DRINK SET name = ?,des=?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3,des);
        statement.bindBlob(4, image);
        statement.bindDouble(5, (double)id);
        statement.execute();
        database.close();
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM DRINK WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_CONTACTS_TABLE =("Create table drink( id integer PRIMARY KEY AUTOINCREMENT,"+
//           "name varchar(30),information varchar(60),price integer,imgid blob)");
//        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

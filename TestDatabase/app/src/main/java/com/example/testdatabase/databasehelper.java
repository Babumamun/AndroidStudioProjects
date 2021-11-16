package com.example.testdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {
public static final String DATABASE_NAME="data.db";
    public static final String TABLE_NAME="data";

    public static final String COLS_1="ID";
    public static final String COLS_2="NAME";
    public static final String COLS_3="ADDRESS";
    public static final String COLS_4="PHONE";
    public static final String COLS_5="EMAIL";

    public databasehelper( Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String m=("CREATE TABLE'data'(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDRESS TEXT,PHONE TEXT, EMAIL TEXT )") ;
        db.execSQL(m);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);
    }
}

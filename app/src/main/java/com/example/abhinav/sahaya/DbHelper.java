package com.example.abhinav.sahaya;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Constants.TABLE_NAME + " ( pillID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, hour INTEGER, minute INTEGER, day_start INTEGER, month_start INTEGER, year_start INTEGER " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

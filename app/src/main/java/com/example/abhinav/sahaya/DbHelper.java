package com.example.abhinav.sahaya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Constants.TABLE_PILLS + " ( pillID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, hour INTEGER, minute INTEGER, " +
                "day_start INTEGER, month_start INTEGER, year_start INTEGER, day_end INTEGER, month_end INTEGER, year_end INTEGER);");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (0, 'Ibuprofeno',5,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (1, 'Ibuprofeno2',6,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (2, 'Ibuprofeno3',7,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (3, 'Ibuprofeno4',13,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (4, 'Ibuprofeno5',14,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (5, 'Ibuprofeno6',15,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (6, 'Ibuprofeno7',21,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (7, 'Ibuprofeno8',22,12,2,1,2016,2,2,2016)");
        db.execSQL("INSERT INTO " + Constants.TABLE_PILLS + " VALUES (8, 'Ibuprofeno9',23,12,2,1,2016,2,2,2016)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PILLS);
        onCreate(db);
    }

    public int getPillID(Cursor c) {
        return c.getInt(c.getColumnIndex("pillID"));
    }
    public String getName(Cursor c) {
        return String.valueOf(c.getString(c.getColumnIndex("name")));
    }
    public int getHour(Cursor c) {
        return c.getInt(c.getColumnIndex("hour"));
    }
    public int getMinute(Cursor c) {
        return c.getInt(c.getColumnIndex("minute"));
    }
    public int getDayStart(Cursor c) {
        return c.getInt(c.getColumnIndex("day_start"));
    }
    public int getMonthStart(Cursor c) {
        return c.getInt(c.getColumnIndex("month_start"));
    }
    public int getYearStart(Cursor c) {
        return c.getInt(c.getColumnIndex("year_start"));
    }
    public int getDayEnd(Cursor c) {
        return c.getInt(c.getColumnIndex("day_end"));
    }
    public int getMonthEnd(Cursor c) {
        return c.getInt(c.getColumnIndex("month_end"));
    }
    public int getYearEnd(Cursor c) {
        return c.getInt(c.getColumnIndex("year_end"));
    }
    /*public boolean isTaken(Cursor c) {
        return c.getString(c.getColumnIndex("pill_taken")).equals("true");
    }*/



}

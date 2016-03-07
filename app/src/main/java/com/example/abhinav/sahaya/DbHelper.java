package com.example.abhinav.sahaya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Constants.TABLE_CALL_NUMBERS + " (idCall INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number TEXT);");
        db.execSQL("INSERT IGNORE INTO " + Constants.TABLE_CALL_NUMBERS + " (name, number) VALUES('', '', '', '')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PILLS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_CALL_NUMBERS);
        onCreate(db);
    }

    public int getCallID(Cursor c) {
        return c.getInt(c.getColumnIndex("idCall"));
    }

    public String getCallName(Cursor c) {
        return c.getString(c.getColumnIndex("name"));
    }

    public String getCallNumber(Cursor c) {
        return c.getString(c.getColumnIndex("number"));
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

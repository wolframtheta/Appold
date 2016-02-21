package com.example.abhinav.sahaya.Pills;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhinav.sahaya.Constants;
import com.example.abhinav.sahaya.Date;
import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.ListViewerAdapter;
import com.example.abhinav.sahaya.R;
import com.example.abhinav.sahaya.Time;

import java.util.ArrayList;
import java.util.HashMap;

public class PillsDayActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_day);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + Constants.TABLE_PILLS + " WHERE hour >= 05 AND hour < 13";

        Cursor c = db.rawQuery(query, null);

        if (c.getCount() < 1) {
            c.close();
            Log.v("NO PILLS MORNING", "CLOSE");
            TextView textVisibleMorning = (TextView) findViewById(R.id.textVisibleMorning);
            textVisibleMorning.setVisibility(View.VISIBLE);
        }
        query = "SELECT * FROM " + Constants.TABLE_PILLS + " WHERE hour >= 13 AND hour < 21";
        c = db.rawQuery(query, null);

        if (c.getCount() < 1) {
            c.close();
            Log.v("NO PILLS AFTERNOON", "CLOSE");
            TextView textVisibleAfternoon = (TextView) findViewById(R.id.textVisibleAfternoon);
            textVisibleAfternoon.setVisibility(View.VISIBLE);
        }
        query = "SELECT * FROM " + Constants.TABLE_PILLS + " WHERE (hour >= 21 AND hour < 24) OR (hour >= 00 AND hour < 5)";
        c = db.rawQuery(query, null);

        if (c.getCount() < 1) {
            c.close();
            Log.v("NO PILLS NIGHT", "CLOSE");
            TextView textVisibleNight = (TextView) findViewById(R.id.textVisibleNight);
            textVisibleNight.setVisibility(View.VISIBLE);
        }

        query = "SELECT * FROM " + Constants.TABLE_PILLS;
        c = db.rawQuery(query, null);
        if (c.getCount() < 1) {
            c.close();
            Log.v("NO PILLS DAY", "CLOSE");
        }
        else {

            ListView listMorning = (ListView)findViewById(R.id.listPillsMorning);
            ListView listAfternoon = (ListView) findViewById(R.id.listPillsAfternoon);
            ListView listNight = (ListView) findViewById(R.id.listPillsNight);
            Pills listPillsMorning = new Pills();
            Pills listPillsAfternoon = new Pills();
            Pills listPillsNight = new Pills();

            while (c.moveToNext()) {
                Pill pill = new Pill();
                pill.setId(dbHelper.getPillID(c));
                pill.setName(dbHelper.getName(c));
                pill.setTime(new Time(dbHelper.getHour(c), dbHelper.getMinute(c)));
                pill.setDate_start(new Date(dbHelper.getDayStart(c), dbHelper.getMonthStart(c), dbHelper.getYearStart(c)));
                pill.setDate_end(new Date(dbHelper.getDayEnd(c), dbHelper.getMonthEnd(c), dbHelper.getYearEnd(c)));
                /*pill.setIsTaken(dbHelper.isTaken(c));
                query = "UPDATE " + Constants.TABLE_PILLS + " SET pill_taken = '" + pill.isTaken() + "' WHERE pillID = " + pill.getId();
                db.execSQL(query);*/
                if (pill.getTime().getHour() >= 05 && pill.getTime().getHour() < 13)
                    listPillsMorning.addPill(pill);
                else if (pill.getTime().getHour() >= 13 && pill.getTime().getHour() < 21)
                    listPillsAfternoon.addPill(pill);
                else
                    listPillsNight.addPill(pill);
            }




            ListViewerAdapter adapterMorning = new ListViewerAdapter(this, listPillsMorning);
            listMorning.setAdapter(adapterMorning);

            listMorning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    int pos = position + 1;
                    Toast.makeText(PillsDayActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                }

            });

            ListViewerAdapter adapterAfternoon = new ListViewerAdapter(this, listPillsAfternoon);
            listAfternoon.setAdapter(adapterAfternoon);

            listAfternoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    int pos=position+1;
                    Toast.makeText(PillsDayActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
                }

            });

            ListViewerAdapter adapterNight = new ListViewerAdapter(this, listPillsNight);
            listNight.setAdapter(adapterNight);

            listNight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    int pos=position+1;
                    Toast.makeText(PillsDayActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
                }

            });

            }


    }


}

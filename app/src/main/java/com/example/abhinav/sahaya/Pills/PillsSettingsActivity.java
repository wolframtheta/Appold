package com.example.abhinav.sahaya.Pills;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abhinav.sahaya.Constants;
import com.example.abhinav.sahaya.Date;
import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.ListViewerAdapter;
import com.example.abhinav.sahaya.R;
import com.example.abhinav.sahaya.Time;

import java.util.ArrayList;
import java.util.List;

public class PillsSettingsActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_settings);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PillNewActivity.class);
                startActivity(intent);
                refresh();
            }
        });

        final ListView listPillsSettings = (ListView) findViewById(R.id.listPillsSetting);


        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        String query = "SELECT name FROM " + Constants.TABLE_PILLS;

        Cursor c = db.rawQuery(query, null);
        String[] list = new String[c.getCount()];
        int i = 0;
        while(c.moveToNext()) {
            list[i] = dbHelper.getName(c);
            ++i;
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);




        listPillsSettings.setAdapter(adapter);


        listPillsSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(), PillEditingActivity.class);
                intent.putExtra("ID", position);
                startActivity(intent);
            }
        });
    }

    public void refresh() {
        final ListView listPillsSettings = (ListView) findViewById(R.id.listPillsSetting);


        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        String query = "SELECT name FROM " + Constants.TABLE_PILLS;

        Cursor c = db.rawQuery(query, null);
        String[] list = new String[c.getCount()];
        int i = 0;
        while(c.moveToNext()) {
            list[i] = dbHelper.getName(c);
            ++i;
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);




        listPillsSettings.setAdapter(adapter);


        listPillsSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(), PillEditingActivity.class);
                intent.putExtra("ID", position);
                startActivity(intent);
            }
        });
    }


}

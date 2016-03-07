package com.example.abhinav.sahaya.Pills;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhinav.sahaya.Constants;
import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.R;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class PillEditingActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor c;
    private Bundle extras;
    private TextView textName;
    private String query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_editing);


        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        query = "";
        textName = (TextView) findViewById(R.id.textName);
        extras = getIntent().getExtras();
        if (extras != null) query = "SELECT * FROM " + Constants.TABLE_PILLS + " WHERE pillID = '" + extras.getInt("ID") + "'";

        c = db.rawQuery(query, null);
        c.moveToNext();
        textName.setText(dbHelper.getName(c));

        ImageView imageEdit = (ImageView) findViewById(R.id.imageEdit);
        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PillEditTimeActivity.class);
                intent.putExtra("ID", extras.getInt("ID"));
                startActivity(intent);
            }
        });

        ImageView imageDelete = (ImageView) findViewById(R.id.imageDelete);
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = "DELETE * FROM " + Constants.TABLE_PILLS + " WHERE pillID = '" + extras.getInt("ID") + "'";
                db.execSQL(query);
                finish();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        String query = "";
        textName = (TextView) findViewById(R.id.textName);
        extras = getIntent().getExtras();
        if (extras != null) query = "SELECT * FROM " + Constants.TABLE_PILLS + " WHERE pillID = '" + extras.getInt("ID") + "'";

        c = db.rawQuery(query, null);
        c.moveToNext();
        textName.setText(dbHelper.getName(c));
    }
}

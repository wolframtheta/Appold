package com.example.abhinav.sahaya.Calls;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.abhinav.sahaya.Constants;
import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.R;

public class CallsActivity extends Activity implements OnClickListener, View.OnLongClickListener {

    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private Button btn5 = null;
    private Button btn6 = null;

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private String no1, no2, no3, no4, no5, no6;
    private SharedPreferences prefs;
    private Cursor c;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        btn1 = (Button) findViewById(R.id.contact1);
        btn2 = (Button) findViewById(R.id.contact2);
        btn3 = (Button) findViewById(R.id.contact3);
        btn4 = (Button) findViewById(R.id.contact4);
        btn5 = (Button) findViewById(R.id.contact5);
        btn6 = (Button) findViewById(R.id.contact6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn1.setLongClickable(true);
        btn2.setLongClickable(true);
        btn3.setLongClickable(true);
        btn4.setLongClickable(true);
        btn5.setLongClickable(true);
        btn6.setLongClickable(true);
        btn1.setOnLongClickListener(this);

        btn2.setOnLongClickListener(this);
        btn3.setOnLongClickListener(this);
        btn4.setOnLongClickListener(this);
        btn5.setOnLongClickListener(this);
        btn6.setOnLongClickListener(this);


        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        for (int i = 0; i < 6; ++i) {
            String query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall ='" + i + "'";
            Cursor c = db.rawQuery(query, null);
            c.moveToNext();
            switch (i) {
                case 0:
                    if (c.getCount() > 0){
                        btn1.setText(dbHelper.getCallName(c));
                        no1 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (0,'','')");
                        btn1.setText("Add");
                    }
                    break;
                case 1:
                    if (c.getCount() > 0){
                        btn2.setText(dbHelper.getCallName(c));
                        no2 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (1,'','')");
                        btn2.setText("Add");
                    }
                    break;
                case 2:
                    if (c.getCount() > 0){
                        btn3.setText(dbHelper.getCallName(c));
                        no3 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (2,'','')");
                        btn3.setText("Add");
                    }
                    break;
                case 3:
                    if (c.getCount() > 0) {
                        btn4.setText(dbHelper.getCallName(c));
                        no4 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (3,'','')");
                        btn4.setText("Add");
                    }
                    break;
                case 4:
                    if (c.getCount() > 0){
                        btn5.setText(dbHelper.getCallName(c));
                        no5 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (4,'','')");
                        btn5.setText("Add");
                    }
                    break;
                case 5:
                    if (c.getCount() > 0){
                        btn6.setText(dbHelper.getCallName(c));
                        no6 = dbHelper.getCallNumber(c);
                    }
                    else {
                        db.execSQL("INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES (5,'','')");
                        btn6.setText("Add");
                    }
                    break;

            }
        }

    }

    @Override
    public boolean onLongClick(View v) {
        String query;
        if (v == btn1) {
            if (!btn1.getText().equals("Add")){
                btn1.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 0";
                db.execSQL(query);

            }
        }
        else if (v == btn2) {
            if (!btn2.getText().equals("Add")){
                btn2.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 1";
                db.execSQL(query);
            }
        }
        else if (v == btn3) {
            if (!btn3.getText().equals("Add")){
                btn3.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 2";
                db.execSQL(query);
            }
        }
        else if (v == btn4) {
            if (!btn4.getText().equals("Add")){
                btn4.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 3";
                db.execSQL(query);
            }
        }
        else if (v == btn5) {
            if (!btn5.getText().equals("Add")){
                btn5.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 4";
                db.execSQL(query);
            }
        }
        else if (v == btn6) {
            if (!btn6.getText().equals("Add")){
                btn6.setText("Add");
                query = "DELETE FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 5";
                db.execSQL(query);
            }
        }

        return false;

    }

    @Override
    public void onClick(View arg0) {
        if (arg0 == btn1) {
            try {
                if (!btn1.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no1));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn2) {
            try {
                if (!btn2.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no2));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn3) {
            try {
                if (!btn3.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no3));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 3);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn4) {
            try {
                if (!btn4.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no4));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 4);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn5) {
            try {
                if (!btn5.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no5));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 5);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn6) {
            try {
                if (!btn6.getText().equals("Add")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no6));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 6);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cur = managedQuery(contactData, null, null, null, null);
                ContentResolver contect_resolver = getContentResolver();

                if (cur.moveToFirst()) {
                    String id = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String name = "";
                    String no = "";

                    Cursor phoneCur = contect_resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    if (phoneCur.moveToFirst()) {
                        name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        no = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }


                    dbHelper = new DbHelper(getApplicationContext());
                    db = dbHelper.getWritableDatabase();
                    String query = "";


                    Log.e("Phone no & name :***: ", name + " : " + no);
                    if(reqCode==1) {
                        no1 = no;
                        btn1.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 0";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no1 + "' WHERE idCall = 0";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('0', '" + name + "', '" + no1 + "');";
                            db.execSQL(query);
                        }

                    }
                    else if (reqCode==2) {
                        no2 = no;
                        btn2.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 1";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no2 + "' WHERE idCall = 1";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('1', '" + name + "', '" + no2 + "');";
                            db.execSQL(query);
                        }

                    }
                    else if (reqCode==3) {
                        no3 = no;
                        btn3.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 2";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no3 + "' WHERE idCall = 2";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('2', '" + name + "', '" + no3 + "');";
                            db.execSQL(query);
                        }
                    }
                    else if (reqCode==4) {
                        no4 = no;
                        btn4.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 3";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no4 + "' WHERE idCall = 3";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('3', '" + name + "', '" + no4 + "');";
                            db.execSQL(query);
                        }
                    }
                    else if (reqCode==5) {
                        no5 = no;
                        btn5.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 4";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no5 + "' WHERE idCall = 4";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('4', '" + name + "', '" + no5 + "');";
                            db.execSQL(query);
                        }
                    }
                    else if (reqCode==6) {
                        no6 = no;
                        btn6.setText(name);
                        query = "SELECT * FROM " + Constants.TABLE_CALL_NUMBERS + " WHERE idCall = 5";
                        c = db.rawQuery(query, null);
                        if (c.moveToNext()) {
                            query = "UPDATE " + Constants.TABLE_CALL_NUMBERS + " SET name = '" + name + "', number = '" + no6 + "' WHERE idCall = 5";
                            db.execSQL(query);
                        }
                        else {
                            query = "INSERT INTO " + Constants.TABLE_CALL_NUMBERS + " VALUES ('5', '" + name + "', '" + no6 + "');";
                            db.execSQL(query);
                        }
                    }


                }

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("IllegalArgumentException :: ", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error :: ", e.toString());
        }
    }
}


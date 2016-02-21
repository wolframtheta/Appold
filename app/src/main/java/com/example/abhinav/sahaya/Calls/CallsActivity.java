package com.example.abhinav.sahaya.Calls;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.R;

public class CallsActivity extends Activity implements OnClickListener {

    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private Button btn5 = null;
    private Button btn6 = null;

    private DbHelper dbHelper;
    private SQLiteDatabase db;


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

      //  dbHelper = new dbHelper (getApplicationContext());
      //  db = dbHelper.getWritableDatabase();
    }



    @Override
    public void onClick(View arg0) {
        if (arg0 == btn1) {

            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn2) {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 2);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn3) {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 3);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn4) {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 4);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn5) {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 5);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Error in intent : ", e.toString());
            }
        }
        else if (arg0 == btn6) {
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 6);
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

                    Log.e("Phone no & name :***: ", name + " : " + no);
                    if(reqCode==1) btn1.setText(name + " : " + no + "\n");
                    else if (reqCode==2) btn2.setText(name + " : " + no + "\n");
                    else if (reqCode==3) btn3.setText(name + " : " + no + "\n");
                    else if (reqCode==4) btn4.setText(name + " : " + no + "\n");
                    else if (reqCode==5) btn5.setText(name + " : " + no + "\n");
                    else if (reqCode==6) btn6.setText(name + " : " + no + "\n");

                    id = null;
                    name = null;
                    no = null;
                    phoneCur = null;
                }
                contect_resolver = null;
                cur = null;
                //                      populateContacts();
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


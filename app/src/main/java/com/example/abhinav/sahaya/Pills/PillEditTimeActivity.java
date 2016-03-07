package com.example.abhinav.sahaya.Pills;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.abhinav.sahaya.Constants;
import com.example.abhinav.sahaya.Date;
import com.example.abhinav.sahaya.DbHelper;
import com.example.abhinav.sahaya.R;
import com.example.abhinav.sahaya.Time;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class PillEditTimeActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private EditText dateTextFrom;
    private EditText dateTextTo;
    private EditText timeText;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Bundle extras;
    private Date startDate;
    private Date endDate;
    private Time time;
    private EditText nameText;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time);
        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        extras = getIntent().getExtras();
        nameText = (EditText) findViewById(R.id.nameText);
        Cursor c = db.rawQuery("SELECT * FROM " + Constants.TABLE_PILLS + " WHERE pillID = '" + extras.getInt("ID") + "'", null);
        c.moveToNext();
        nameText.setText(dbHelper.getName(c));
        dateTextFrom = (EditText) findViewById(R.id.dateTextFrom);
        dateTextTo = (EditText) findViewById(R.id.dateTextTo);
        startDate = new Date(dbHelper.getDayStart(c),dbHelper.getMonthStart(c),dbHelper.getYearStart(c));
        endDate = new Date(dbHelper.getDayEnd(c),dbHelper.getMonthEnd(c),dbHelper.getYearEnd(c));
        time = new Time(dbHelper.getHour(c),dbHelper.getMinute(c));
        dateTextFrom.setText(startDate.getDay() + "/" + startDate.getMonth() + "/" + startDate.getYear());
        dateTextFrom.setOnClickListener(this);
        dateTextFrom.setOnFocusChangeListener(this);
        dateTextTo.setOnFocusChangeListener(this);
        dateTextTo.setOnClickListener(this);
        dateTextTo.setText(endDate.getDay() + "/" + endDate.getMonth() + "/" + endDate.getYear());
        timeText = (EditText) findViewById(R.id.hourText);
        timeText.setOnClickListener(this);
        timeText.setText(time.getHour() + ":" + time.getMinute());
        timeText.setOnFocusChangeListener(this);
        timeText.setInputType(InputType.TYPE_NULL);
        dateTextFrom.setInputType(InputType.TYPE_NULL);
        dateTextTo.setInputType(InputType.TYPE_NULL);




        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extras != null) {
                    String query = "UPDATE " + Constants.TABLE_PILLS + " SET name = '" + nameText.getText() + "', day_start = '" + startDate.getDay() + "', month_start = '" + startDate.getMonth() + "', " +
                            " year_start = '" + startDate.getYear() + "', day_end = '" + endDate.getDay() + "', month_end = '" + endDate.getMonth() + "', year_end = '" +
                            endDate.getYear() + "', hour = '" + time.getHour() + "', minute = '" + time.getMinute() + "' WHERE pillID = '" + extras.getInt("ID") + "'";

                    db.execSQL(query);
                    finish();
                }
            }
        });

    }



    @Override
    public void onClick(View v) {

        if (v == timeText) {
            // Process to get Current Time


            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, final int hourOfDay,
                                             final int minute) {
                            // Display Selected time in textbox

                            timeText.setText(hourOfDay + ":" + minute);
                        }
                    }, time.getHour(), time.getMinute(), true);
            tpd.show();
        }
        if (v == dateTextFrom) {
            // Process to get Current Date


            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextFrom.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, startDate.getYear(), startDate.getMonth(), startDate.getDay());
            dpd.show();

            dateTextTo.setEnabled(true);
        }
        if (v == dateTextTo) {

            DatePickerDialog dpd2 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextTo.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, endDate.getYear(), endDate.getMonth(), endDate.getDay());
            dpd2.show();
            timeText.setEnabled(true);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v == timeText && hasFocus) {
            // Process to get Current Time


            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox

                            timeText.setText(hourOfDay + ":" + minute);
                        }
                    }, time.getHour(), time.getMinute(), true);
            tpd.show();
        }
        if (v == dateTextFrom && hasFocus) {
            // Process to get Current Date


            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextFrom.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, startDate.getYear(), startDate.getMonth(), startDate.getDay());
            dpd.show();
            dateTextTo.setEnabled(true);
        }
        if (v == dateTextTo && hasFocus) {

            DatePickerDialog dpd2 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextTo.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, endDate.getYear(), endDate.getMonth(), endDate.getDay());
            dpd2.show();
            timeText.setEnabled(true);
        }
    }
}

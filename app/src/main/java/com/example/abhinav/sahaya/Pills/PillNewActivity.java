package com.example.abhinav.sahaya.Pills;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import java.util.Calendar;

/**
 * Created by Xavier Marquès on 21/02/16.
 */
public class PillNewActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener{
    private EditText dateTextFrom;
    private EditText dateTextTo;
    private EditText timeText;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Bundle extras;
    private Date startDate;
    private Date endDate;
    private Time time;
    private int mYear, mMonth, mDay, mYear2, mMonth2, mDay2;
    private int mHour, mMinute;
    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pill);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        nameText = (EditText) findViewById(R.id.nameText);
        dateTextFrom = (EditText) findViewById(R.id.dateTextFrom);
        dateTextTo = (EditText) findViewById(R.id.dateTextTo);
        startDate = new Date(0,0,0);
        endDate = new Date(0,0,0);
        time = new Time(0,0);
        dateTextFrom.setOnClickListener(this);
        dateTextFrom.setOnFocusChangeListener(this);
        dateTextTo.setOnFocusChangeListener(this);
        dateTextTo.setOnClickListener(this);
        timeText = (EditText) findViewById(R.id.hourText);
        timeText.setOnClickListener(this);
        timeText.setOnFocusChangeListener(this);
        timeText.setInputType(InputType.TYPE_NULL);
        dateTextFrom.setInputType(InputType.TYPE_NULL);
        dateTextTo.setInputType(InputType.TYPE_NULL);




        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate.setDay(mDay);
                startDate.setMonth(mMonth);
                startDate.setYear(mYear);
                endDate.setDay(mDay2);
                endDate.setMonth(mMonth2);
                endDate.setYear(mYear2);
                time.setHour(mHour);
                time.setMinute(mMinute);

                    String query = "INSERT INTO " + Constants.TABLE_PILLS + " (name, hour, minute, day_start, month_start, year_start, day_end, month_end, year_end) VALUES('" + nameText.getText() + "', '"  + time.getHour() + "', '" + time.getMinute() + "', '" + startDate.getDay() + "', '" + startDate.getMonth() + "', " +
                            "'" + startDate.getYear() + "', '" + endDate.getDay() + "', '" + endDate.getMonth() + "', '" + endDate.getYear() + "')";

                    db.execSQL(query);
                    finish();

            }
        });

    }



    @Override
    public void onClick(View v) {

        if (v == timeText) {
            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, final int hourOfDay,
                                              final int minute) {
                            // Display Selected time in textbox

                            timeText.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            tpd.show();
        }
        if (v == dateTextFrom) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

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
                    }, mYear, mMonth, mDay);
            dpd.show();

            dateTextTo.setEnabled(true);
        }
        if (v == dateTextTo) {
            final Calendar c = Calendar.getInstance();
            mYear2 = c.get(Calendar.YEAR);
            mMonth2 = c.get(Calendar.MONTH);
            mDay2 = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd2 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextTo.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear2, mMonth2, mDay2);
            dpd2.show();
            timeText.setEnabled(true);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v == timeText && hasFocus) {
            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox

                            timeText.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            tpd.show();
        }
        if (v == dateTextFrom && hasFocus) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

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
                    }, mYear, mMonth, mDay);
            dpd.show();
            dateTextTo.setEnabled(true);
        }
        if (v == dateTextTo && hasFocus) {
            final Calendar c = Calendar.getInstance();
            mYear2 = c.get(Calendar.YEAR);
            mMonth2 = c.get(Calendar.MONTH);
            mDay2 = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd2 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dateTextTo.setText(dayOfMonth + "/"
                                    + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear2, mMonth2, mDay2);
            dpd2.show();
            timeText.setEnabled(true);
        }
    }
}

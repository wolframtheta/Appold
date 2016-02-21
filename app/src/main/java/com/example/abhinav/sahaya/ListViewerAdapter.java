package com.example.abhinav.sahaya;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.abhinav.sahaya.Pills.Pill;
import com.example.abhinav.sahaya.Pills.Pills;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class ListViewerAdapter extends BaseAdapter {
    public Pills pills;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    public ListViewerAdapter(Activity activity, Pills pills){
        super();
        this.activity=activity;
        this.pills=pills;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pills.getSize();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if (pills != null)
            return pills.getPill(position);
        return null;
    }

    @Override
    public long getItemId(int id) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);
           // isTaken = (CheckBox) convertView.findViewById(R.id.isTaken);
            txtFirst=(TextView) convertView.findViewById(R.id.name);
            txtSecond=(TextView) convertView.findViewById(R.id.gender);

        }

        Pill pill;
        if (pills != null) {
            pill = pills.getPill(position);
            txtFirst.setText(pill.getName());
            txtSecond.setText(String.valueOf(pill.getTime().getHour()) + ":" + String.valueOf(pill.getTime().getMinute()));
           /* isTaken.setChecked(pill.isTaken());
            if (pill.isTaken()) {
                txtFirst.setPaintFlags(txtFirst.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
                txtSecond.setPaintFlags(txtFirst.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
            }

            isTaken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTaken.isChecked()) {
                        txtFirst.setPaintFlags(txtFirst.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
                        txtSecond.setPaintFlags(txtSecond.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
                    } else {
                        txtFirst.setPaintFlags(0);
                        txtSecond.setPaintFlags(0);
                    }
                }
            });*/
        }



        return convertView;
    }
}

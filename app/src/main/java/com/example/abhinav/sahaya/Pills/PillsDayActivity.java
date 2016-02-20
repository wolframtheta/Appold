package com.example.abhinav.sahaya.Pills;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abhinav.sahaya.ListViewerAdapter;
import com.example.abhinav.sahaya.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PillsDayActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_day);

        ListView listView=(ListView)findViewById(R.id.listView1);

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put("First", "Ankit Karia");
        temp.put("Second", "Male");
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put("First", "Rajat Ghai");
        temp2.put("Second", "Male");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put("First", "Karina Kaif");
        temp3.put("Second", "Female");
        list.add(temp3);

        ListViewerAdapter adapter=new ListViewerAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(PillsDayActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
            }

        });

    }


}

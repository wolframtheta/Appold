package com.example.abhinav.sahaya;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.abhinav.sahaya.Calls.CallsActivity;
import com.example.abhinav.sahaya.Pills.PillsMainActivity;
import com.example.abhinav.sahaya.Search.SearchActivity;
import com.example.abhinav.sahaya.Zoom.ZoomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        ImageView imageSearch = (ImageView) findViewById(R.id.imageSearch);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageCalls = (ImageView) findViewById(R.id.imageCalls);
        imageCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CallsActivity.class);
                startActivity(intent);
            }
        });
        ImageView imagePills = (ImageView) findViewById(R.id.imagePills);
        imagePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PillsMainActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageZoom = (ImageView) findViewById(R.id.imageZoom);
        imageZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ZoomActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

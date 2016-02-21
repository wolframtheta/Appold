package com.example.abhinav.sahaya.Pills;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.abhinav.sahaya.R;

public class PillsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);


        ImageView imageClock = (ImageView) findViewById(R.id.imageClock);
        imageClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PillsDayActivity.class);

                startActivity(intent);

            }
        });

        ImageView imageSettings = (ImageView) findViewById(R.id.imageSettings);
        imageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PillsSettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}

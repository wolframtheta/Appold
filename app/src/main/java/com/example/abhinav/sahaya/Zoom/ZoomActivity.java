package com.example.abhinav.sahaya.Zoom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abhinav.sahaya.R;

public class ZoomActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buttonStartCameraPreview = (Button) findViewById(R.id.startcamerapreview);
        buttonStopCameraPreview = (Button) findViewById(R.id.stopcamerapreview);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


}

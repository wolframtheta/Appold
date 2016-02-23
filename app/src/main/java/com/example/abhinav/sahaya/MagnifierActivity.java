package com.example.abhinav.sahaya;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ZoomControls;

import java.util.List;


/**
 * Created by Xavier Marquès on 23/02/16.
 */
public class MagnifierActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Button Left, Right, Up, Down, NV, Lzo, Mzo;
    private boolean NVon, NVable;
    private Camera cam;
    Camera.Parameters par;
    SurfaceView sView;
    SurfaceHolder sHolder;
    ImageView xhair;
    ZoomControls zoomControls;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private int AimX, AimY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnifier);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



    sView = (SurfaceView) findViewById(R.id.surfaceView);
    sView.getHolder().addCallback(this);
    sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);

    getCamera();

    zoomControls = (ZoomControls) findViewById(R.id.zoomControls);

    if (par.isZoomSupported()) {

        zoomControls.setVisibility(View.GONE);
        final int maxZoomLevel = par.getMaxZoom();
        Log.i("max ZOOM ", "is " + maxZoomLevel);
        zoomControls.setIsZoomInEnabled(true);
        zoomControls.setIsZoomOutEnabled(true);



        par.setZoom(par.getMaxZoom() / 2);
        if (par.getSupportedFocusModes().contains(
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
            par.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        }
        cam.setParameters(par);

    }






}


    private void getCamera() {
        if (cam == null) {
            try {
                cam = Camera.open();
                par = cam.getParameters();
            } catch (Exception e) {

            }
        }
    }

    private void turnOnFlash() {

        if (!NVon) {
            if (cam == null || par == null) {
                return;
            }

            par = cam.getParameters();
            par.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(par);
            cam.startPreview();
            NVon = true;
        }

    }

    private void turnOffFlash() {

        if (NVon) {
            if (cam == null || par == null) {
                return;
            }

            par = cam.getParameters();
            par.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            cam.setParameters(par);
            //cam.stopPreview();
            NVon = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cam.stopPreview();
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cam.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCamera();
        cam.startPreview();
        // on resume turn on the flash
        if (NVable) turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        // on starting the app get the camera params
        getCamera();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        // on stop release the camera
        if (cam != null) {
            cam.release();
            cam = null;
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = cam.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width, selected.height);
        cam.setParameters(params);

        cam.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            cam.setPreviewDisplay(sView.getHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("PREVIEW", "surfaceDestroyed");
    }
}

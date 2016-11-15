package cpsc481.fall2016.uofcreccentret04;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.w3c.dom.Text;

import java.io.IOException;

public class levelup extends AppCompatActivity {

    // Menu Dock Object
    MenuDock md;

    // Bar Code Objects
    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private boolean scanstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelup);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customaction_bar);
        TextView tv = (TextView) findViewById(R.id.headerText);
        tv.setText(R.string.levelup);

        scanstate = false;

        // Menu Dock Init
        md = new MenuDock(this);

        // Bar Code Scanner
        // https://code.tutsplus.com/tutorials/reading-qr-codes-using-the-mobile-vision-api--cms-24680

        cameraView = (SurfaceView)findViewById(R.id.levelup_camera);
        barcodeInfo = (TextView)findViewById(R.id.levelup_code);
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() { // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(barcodes.valueAt(0).displayValue);
                            Button b = (Button) findViewById(R.id.levelup_scanb);
                            b.setText(R.string.startscan);
                            cameraSource.stop();
                            scanstate = false;
                        }
                    });
                }
            }
        });

        // END OF BAR CODE
    }

    public void levelup_scannow(View view) {

        if (scanstate) {
            scanstate = false;
            cameraSource.stop();
            Button b = (Button) findViewById(R.id.levelup_scanb);
            b.setText(R.string.startscan);
        }
        else {
            scanstate = true;
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException ie) {
                Log.e("CAMERA SOURCE", ie.getMessage());
            }
            Button b = (Button) findViewById(R.id.levelup_scanb);
            b.setText(R.string.stopscan);
        }
    }

    @Override
    public void onBackPressed() {
        if (md.menu_dock != null &&
                (md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            md.menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}

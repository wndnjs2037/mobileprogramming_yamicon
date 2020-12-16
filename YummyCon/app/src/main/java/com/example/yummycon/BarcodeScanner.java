package com.example.yummycon;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class BarcodeScanner extends AppCompatActivity {
    private CaptureManager captur;
    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_scanner);

        barcodeScannerView =(DecoratedBarcodeView) findViewById(R.id.dbvBarcode);

        captur = new CaptureManager(this, barcodeScannerView);
        captur.initializeFromIntent(this.getIntent(),savedInstanceState);
        captur.decode();
        barcodeScannerView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                readBarcode(result.toString());
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        captur.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        captur.onPause();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        captur.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        captur.onSaveInstanceState(outState);
    }

    private void readBarcode(String barcode) {
        Toast.makeText(getApplicationContext(), barcode, Toast.LENGTH_LONG).show();
    }
}

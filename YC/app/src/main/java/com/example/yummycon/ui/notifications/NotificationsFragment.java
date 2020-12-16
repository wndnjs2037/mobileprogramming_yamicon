package com.example.yummycon.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import com.example.yummycon.R;

public class NotificationsFragment extends Fragment {

    private CaptureManager captur;
    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notifications);

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
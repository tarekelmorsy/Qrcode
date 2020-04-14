   package com.example.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

   public class MainActivity extends AppCompatActivity {

    Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan= findViewById(R.id.scan_btn);
        final Activity activity=this;
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator= new IntentIntegrator(activity);
               // integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }

       @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

           IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
           if (result!= null){

               if (result.getContents()==null){

                   Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(this, result.getFormatName()+ "  "+ result.getContents(), Toast.LENGTH_SHORT).show();

                  // Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
               }
           }
           else {

           super.onActivityResult(requestCode, resultCode, data);
       }}
   }

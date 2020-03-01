package com.fiebig.blutzuckerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String server_url = "http://192.168.178.59/blutzuckerapp_api/blutzucker.php?user=1";
    Dialog neueMessung;
    Button neueMessungBtn, neueMessungGeraet;
    ImageView neueMessungClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        neueMessung = new Dialog(this, R.style.Popup);

        textView = findViewById(R.id.DBText);
        neueMessungBtn = findViewById(R.id.neueMessungBtn);

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response + " mg/dl");
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error){
                    textView.setText(error.toString());
                    requestQueue.stop();
                }
            });
        requestQueue.add(stringRequest);

        neueMessungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neueMessung.setContentView(R.layout.neue_messung_popup);
                neueMessung.show();
                neueMessungClose = (ImageView) neueMessung.findViewById(R.id.neueMessungClose);

                neueMessungGeraet = neueMessung.findViewById(R.id.neueMessungGeraet);

                neueMessungClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        neueMessung.dismiss();
                    }
                });


                neueMessungGeraet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread neueMessungThread = new Thread() {

                            @Override
                            public void run() {
                                try {
                                    neueMessung.dismiss();
                                    super.run();
                                } catch (Exception e) {

                                } finally {

                                    Intent i = new Intent(MainActivity.this, NeueMessungActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        };
                        neueMessungThread.start();
                    }
                });
            }
        });

    }
}

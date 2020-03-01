package com.fiebig.blutzuckerapp;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NeueMessungActivity extends AppCompatActivity {
    String server_url = "http://192.168.178.59/blutzuckerapp_api/blutzucker.php?user=1";
    ImageView neueMessungAbbrechen, neueMessungBestaetigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_messung);

        neueMessungAbbrechen = findViewById(R.id.neueMessungAbbrechen);
        neueMessungBestaetigen = findViewById(R.id.neueMessungBestaetigen);

        neueMessungAbbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread neueMessungAbbrechenThread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            super.run();
                        } catch (Exception e) {

                        } finally {

                            Intent i = new Intent(NeueMessungActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                };
                neueMessungAbbrechenThread.start();
            }
        });

        neueMessungBestaetigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread neueMessungBearbeitenThread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            super.run();
                        } catch (Exception e) {

                        } finally {

                            Intent i = new Intent(NeueMessungActivity.this, NeueMessungBearbeitenActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                };
                neueMessungBearbeitenThread.start();
            }
        });
    }
}

package com.fiebig.blutzuckerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NeueMessungBearbeitenActivity extends AppCompatActivity {
    String server_url = "http://192.168.178.59/blutzuckerapp_api/blutzucker.php?user=1";
    ImageView neueMessungBearbeitenAbbrechen, neueMessungBearbeitenBestaetigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_messung_bearbeiten);

        neueMessungBearbeitenAbbrechen = findViewById(R.id.neueMessungBearbeitenAbbrechen);
        neueMessungBearbeitenBestaetigen = findViewById(R.id.neueMessungBearbeitenBestaetigen);

        neueMessungBearbeitenAbbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread neueMessungBearbeitenAbbrechenThread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            super.run();
                        } catch (Exception e) {

                        } finally {

                            Intent i = new Intent(NeueMessungBearbeitenActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                };
                neueMessungBearbeitenAbbrechenThread.start();
            }
        });

        neueMessungBearbeitenBestaetigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread neueMessungBearbeitenBestaetigenThread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            super.run();
                        } catch (Exception e) {

                        } finally {

                            Intent i = new Intent(NeueMessungBearbeitenActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                };
                neueMessungBearbeitenBestaetigenThread.start();
            }
        });
    }
}

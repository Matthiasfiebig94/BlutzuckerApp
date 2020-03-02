package com.fiebig.blutzuckerapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NeueMessungBearbeitenActivity extends AppCompatActivity {
    String server_url = "http://192.168.178.59/blutzuckerapp_api/blutzucker.php?user=1";
    ImageView neueMessungBearbeitenAbbrechen, neueMessungBearbeitenBestaetigen, lebensmittelPopupClose;
    EditText neueMessungMesswert;
    Dialog gegessenPopup;
    Button neueMessungGegessen;
    Spinner lebensmittelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_messung_bearbeiten);

        neueMessungBearbeitenAbbrechen = findViewById(R.id.neueMessungBearbeitenAbbrechen);
        neueMessungBearbeitenBestaetigen = findViewById(R.id.neueMessungBearbeitenBestaetigen);
        neueMessungMesswert = findViewById(R.id.neueMessungMesswert);
        neueMessungGegessen = findViewById(R.id.neueMessungGegessen);
        gegessenPopup = new Dialog(this, R.style.Popup);

        final RequestQueue requestQueue = Volley.newRequestQueue(NeueMessungBearbeitenActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        neueMessungMesswert.setText(response + " mg/dl");
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                neueMessungMesswert.setText(error.toString());
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

        neueMessungGegessen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gegessenPopup.setContentView(R.layout.neue_messung_lebensmittel_popup);
                gegessenPopup.show();
                lebensmittelPopupClose = (ImageView) gegessenPopup.findViewById(R.id.lebensmittelPopupClose);
                lebensmittelSpinner = gegessenPopup.findViewById(R.id.lebensmittelSpinner);

                String[] items = new String[]{"Banane", "Brot", "Traubenzucker"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

                lebensmittelPopupClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gegessenPopup.dismiss();
                    }
                });


                lebensmittelPopupClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread neueMessungThread = new Thread() {

                            @Override
                            public void run() {
                                try {
                                    gegessenPopup.dismiss();
                                    super.run();
                                } catch (Exception e) {

                                } finally {
                                }
                            }
                        };
                        neueMessungThread.start();
                    }
                });
            }
        });





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

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
    TextView textView, circleGreen, circleYellow, circleRed, textView2;
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
        circleGreen = findViewById(R.id.circle_green);
        circleYellow = findViewById(R.id.circle_yellow);
        circleRed = findViewById(R.id.circle_red);
        textView = findViewById(R.id.DBText);
        textView2 = findViewById(R.id.DBText2);
        neueMessungBtn = findViewById(R.id.neueMessungBtn);

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response + " mg/dl");
                        int messwert = Integer.parseInt(response);
                        if(messwert >= 80 && messwert <= 150)
                        {
                            //Gut
                            textView2.setText("Ihr Blutzuckerspiegel ist im optimalen Bereich");
                            circleGreen.setAlpha(1);
                            circleYellow.setAlpha(0);
                            circleRed.setAlpha(0);
                        }
                        else if(messwert <= 79 && messwert >= 50)
                        {
                            //mittel
                            textView2.setText("Ihr Blutzuckerspiegel ist im leicht verringert");
                            circleGreen.setAlpha(0);
                            circleYellow.setAlpha(1);
                            circleRed.setAlpha(0);
                        }
                        else if(messwert >= 151 && messwert <= 180)
                        {
                            //mittel
                            textView2.setText("Ihr Blutzuckerspiegel ist im leicht erhöht");
                            circleGreen.setAlpha(0);
                            circleYellow.setAlpha(1);
                            circleRed.setAlpha(0);
                        }
                        else if(messwert >= 181)
                        {
                            //Schlecht
                            textView2.setText("Ihr Blutzuckerspiegel ist stark erhöht");
                            circleGreen.setAlpha(0);
                            circleYellow.setAlpha(0);
                            circleRed.setAlpha(1);
                        }
                        else if(messwert <= 49)
                        {
                            //Schlecht
                            textView2.setText("Ihr Blutzuckerspiegel ist stark verringert");
                            circleGreen.setAlpha(0);
                            circleYellow.setAlpha(0);
                            circleRed.setAlpha(1);
                        }
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

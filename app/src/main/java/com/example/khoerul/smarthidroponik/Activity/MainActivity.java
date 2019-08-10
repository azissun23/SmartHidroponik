package com.example.khoerul.smarthidroponik.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khoerul.smarthidroponik.R;
import com.example.khoerul.smarthidroponik.Sensor;
import com.example.khoerul.smarthidroponik.SensorAdapter;
import com.example.khoerul.smarthidroponik.Tanaman;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    Button infotanaman;
    @BindView(R.id.sensorset)
    RecyclerView sensorset;
    SensorAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    ToggleButton toggleButton;
    private static final String URL = "http://hidroponik.96.lt/getalat.php";

//    private Spinner spinernutria;

    private EditText pomnutrisiA, pomnutrisiB, pomairBasa;
    private Button validinput;
    private static String URL_POMPA = "http://hidroponik.96.lt/CONFIG/pompa.php";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        spinernutria = (Spinner)findViewById(R.id.validnutrisiA) ;

        pomnutrisiA = findViewById(R.id.validnutrisiA);
        pomnutrisiB = findViewById(R.id.validnutrisiB);
        pomairBasa = findViewById(R.id.validasiAir);
        validinput = findViewById(R.id.validasiinutrisi);

        validinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputVAlidasi();
            }
        });

        toggleButton = (ToggleButton) findViewById(R.id.pompatandon);

        infotanaman = (Button) findViewById(R.id.btn_infotanaman);
        infotanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tanaman.class);
                startActivity(intent);
            }
        });


        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
//                validinput.setVisibility(View.VISIBLE);
                String url = "http://hidroponik.96.lt/getalat.php";
                DemoAsync demoASync = new DemoAsync();
                demoASync.execute(url);
            }
        });


        ButterKnife.bind(this);
        adapter = new SensorAdapter(this);
        String url = "http://hidroponik.96.lt/getalat.php";
        DemoAsync demoASync = new DemoAsync();
        demoASync.execute(url);
    }

    private void InputVAlidasi() {
//        validinput.setVisibility(View.GONE);


        final String pomnutrisiA = this.pomnutrisiA.getText().toString().trim();
        final String pomnutrisiB = this.pomnutrisiB.getText().toString().trim();
        final String pomairBasa = this.pomairBasa.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POMPA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");
                            if (succes.equals("1")) {
                                Toast.makeText(MainActivity.this, "Input Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Input Gagal!!!", Toast.LENGTH_SHORT).show();
                            validinput.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Input Gagal!!!", Toast.LENGTH_SHORT).show();
                        validinput.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Log.d("test put", "getParams: " + pomnutrisiA);
                params.put("pomnutrisiA", pomnutrisiA);
                Log.d("Test A", "getParams: " + pomnutrisiA);
                params.put("pomnutrisiB", pomnutrisiB);
                Log.d("Test B", "getParams: " + pomnutrisiB);
                params.put("pomairBasa", pomairBasa);
                Log.d("Test C", "getParams: " + pomairBasa);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ingin Keluar Aplikasi?").setCancelable(false).setPositiveButton(
                "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void pompa(View view) {
        if (toggleButton.isChecked())
            Toast.makeText(this, "Pompa Aktif", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Pompa Mati", Toast.LENGTH_LONG).show();
    }


    public class DemoAsync extends AsyncTask<String, Void, ArrayList<Sensor>> {


        @Override
        protected ArrayList<Sensor> doInBackground(String... strings) {
            String uri = strings[0];
            final ArrayList<Sensor> sensors = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();

            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.get(uri, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String hasil = new String(responseBody);
                        JSONObject jsonData = new JSONObject(hasil);
                        JSONArray jsonArray = jsonData.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject sensorObj = jsonArray.getJSONObject(i);
                            Sensor sensor = new Sensor(sensorObj);

                            sensors.add(sensor);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Tag", "onFailure: " + statusCode);
                }
            });
            return sensors;
        }

        @Override
        protected void onPostExecute(ArrayList<Sensor> sensor) {
            super.onPostExecute(sensor);
            sensorset.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.setListSensor(sensor);
            sensorset.setAdapter(adapter);
        }
    }
}
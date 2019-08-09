package com.example.khoerul.smarthidroponik.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String URL = "http://hidroponik.96.lt/getalat.php";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        for (int i = 0; i < jsonArray.length(); i++){
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
        protected void onPostExecute (ArrayList<Sensor> sensor){
            super.onPostExecute(sensor);
            sensorset.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.setListSensor(sensor);
            sensorset.setAdapter(adapter);
        }
    }
}
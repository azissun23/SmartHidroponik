package com.example.khoerul.smarthidroponik.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khoerul.smarthidroponik.POJO.MD5;
import com.example.khoerul.smarthidroponik.POJO.User;
import com.example.khoerul.smarthidroponik.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.email_address)
    EditText mEmail;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.no_account)
    TextView mBtnSingUp;
    User user;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mBtnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                Log.d(TAG, "onClick: " + email);
                Log.d(TAG, "onClick: " + password);
                boolean kosong = true;
                if (TextUtils.isEmpty(email)) {

                    kosong = false;
                    mEmail.setError("Tidak Boleh Kosong");

                }
                if (TextUtils.isEmpty(password)) {
                    kosong = false;
                    mPassword.setError("Tidak Boleh Kosong");
                }
                else{
                    cekUser();
                }
            }
        });
    }

    private void cekUser() {
        String URL = "http://hidroponik.96.lt/getuser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    final JSONArray jsonArray = object.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        User userUrl = new User(object1);


                        Log.d(TAG, "onResponse: Url " + userUrl.getEmail());
                        Log.d(TAG, "onResponse: " + email);

                        Log.d(TAG, "onResponse: Url" + userUrl.getPassword());
                        Log.d(TAG, "onResponse: " + password);


                        if (userUrl.getEmail().equalsIgnoreCase(email) && userUrl.getPassword().equals(password)) {

                            Log.d(TAG, "onResponse: user id "+userUrl.getIdUser());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);

    }
}
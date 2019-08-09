package com.example.khoerul.smarthidroponik.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.khoerul.smarthidroponik.Activity.LoginActivity;
import com.example.khoerul.smarthidroponik.R;

public class SplashscreenActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        button =(Button) findViewById(R.id.btn_login_splashscreen);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}

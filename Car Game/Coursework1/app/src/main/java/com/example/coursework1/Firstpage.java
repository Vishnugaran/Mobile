package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Firstpage extends AppCompatActivity {
    //First Screen Timer
    //Reference  www.youtube.com/watch?v=jXtof6OUtcE
private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Phone Full Scree view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_firstpage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Firstpage.this,MainActivity.class);
                startActivity(homeIntent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
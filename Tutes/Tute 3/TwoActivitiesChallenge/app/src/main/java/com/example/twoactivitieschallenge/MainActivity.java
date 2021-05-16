package com.example.twoactivitieschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String BUTTON_ID = "com.project.twoactivitieschallenge.extra.BUTTON_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        int buttonID = view.getId();
        intent.putExtra(BUTTON_ID, buttonID);
        startActivity(intent);
    }
}
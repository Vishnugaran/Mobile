package com.example.twoactivitieschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int buttonID = intent.getIntExtra(MainActivity.BUTTON_ID, 0);
        TextView textView = findViewById(R.id.text_passage);
        switch (buttonID) {
            case R.id.button_one:
                textView.setText(R.string.text_passage_one);
                break;
            case R.id.button_two:
                textView.setText(R.string.text_passage_two);
                break;
            case R.id.button_three:
                textView.setText(R.string.text_passage_three);
                break;
        }
    }
}
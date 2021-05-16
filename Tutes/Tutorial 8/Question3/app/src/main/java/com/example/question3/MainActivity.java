package com.example.question3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearlayout;
    int count = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearlayout = findViewById(R.id.linearlayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        CheckBox chk1 = new CheckBox(this);
        chk1.setText("Option 1");
        chk1.setLayoutParams(params);
        CheckBox chk2 = new CheckBox(this);
        chk2.setText("Option 2");
        chk2.setLayoutParams(params);
        CheckBox chk3 = new CheckBox(this);
        chk3.setText("Option 3");
        chk3.setLayoutParams(params);
        linearlayout.addView(chk1);
        linearlayout.addView(chk2);
        linearlayout.addView(chk3);
    }
    public void onClick(View view) {
        LinearLayout lin1 = new LinearLayout(this);
        lin1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.CENTER;
        CheckBox chk = new CheckBox(this);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.BLACK);
        tv.setText("Option " + count);
        ++count;
        lin1.addView(chk);
        lin1.addView(tv);
        lin1.setLayoutParams(params1);
        linearlayout.addView(lin1);
    }
}
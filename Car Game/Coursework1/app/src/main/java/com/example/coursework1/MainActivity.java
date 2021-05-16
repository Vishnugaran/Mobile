package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Button carMake,hints,carimage,adlevel;
    Switch aSwitch;
    String r="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        carMake =(Button)findViewById(R.id.carmake);
        hints =(Button)findViewById(R.id.hints);
        carimage =(Button)findViewById(R.id.carimage);
        adlevel =(Button)findViewById(R.id.advancedlevel);
        aSwitch = (Switch)findViewById(R.id.switch1);

        carMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IdentifyTheCarMake.class);
                intent.putExtra("Switch",r);
                startActivity(intent);
            }
        });

        hints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Hints.class);
                intent.putExtra("Switch",r);
                startActivity(intent);
            }
        });

        carimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IdentifytheCarImage.class);
                intent.putExtra("Switch",r);
                startActivity(intent);
            }
        });

        adlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdvancedLevel.class);
                intent.putExtra("Switch",r);
                startActivity(intent);
            }
        });
        if(r.equals("true")){
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    r="true";
                }else{
                    r="false";
                }
            }
        });

    }



}
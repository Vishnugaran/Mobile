package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyTheCarMake extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Integer[] carArray;

TextView text,textView2,text_timer;
Object[] bens;
Object[] bmw;
Object[] ford;
Object[] fre;
Object[] volk;
Object[] jag;
ArrayList<Integer> carlist;
Random random;
ImageView imageView;
Spinner spinner;
Button submit;
int number,list_no;
String name;
boolean kkk;
String switch_id;
private long timeMillisecs = 20000;
CountDownTimer countDownTimer;

int  no = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Phone Full Scree view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_identify_the_car_make);
        text = (TextView)findViewById(R.id.answer);
        textView2 = (TextView)findViewById(R.id.textView3);
        text_timer = (TextView)findViewById(R.id.timer);
        carlist = new ArrayList<>();
        random = new Random();
        bens = new Object[5];
        bmw = new Object[5];
        fre = new Object[5];
        jag = new Object[5];
        ford = new Object[5];
        volk = new Object[5];
        imageView =(ImageView)findViewById(R.id.imageid);
        spinner =(Spinner) findViewById(R.id.spinnerid);
        submit =(Button)findViewById(R.id.submit);
        switch_id = getIntent().getStringExtra("Switch");

//        carArray = new Integer[]{R.drawable.bens1,R.drawable.bens2,R.drawable.bens3,R.drawable.bens4,R.drawable.bens5,R.drawable.bmw1,R.drawable.bmw2,R.drawable.bmw3,R.drawable.bmw4,R.drawable.bmw5,R.drawable.ford1,R.drawable.ford2,R.drawable.ford3,R.drawable.ford4,R.drawable.ford5,R.drawable.fre1,R.drawable.fre2,R.drawable.fre3,R.drawable.fre4,R.drawable.fre5,R.drawable.jag1,R.drawable.jag2,R.drawable.jag3,R.drawable.jag4,R.drawable.jag5,R.drawable.volk1,R.drawable.volk2,R.drawable.volk3,R.drawable.volk4,R.drawable.volk5};
//        System.out.println("heeeee "+ getIntent().getStringExtra("Switch"));

        //for loops are used to retrieve the images from drawable folder and store it to array list and arrays
        for (int i = 0; i < 5; i++) {
            try {
                //using getResources you can retrieve the images from drawable
                //Reference stackoverflow
            int imgid = getResources().getIdentifier("bens"+i,"drawable",getPackageName());
            System.out.println(imgid);

                System.out.println(i);
                carlist.add(imgid);
                bens[i] = imgid;


            int imgid1 = getResources().getIdentifier("bmw"+i,"drawable",getPackageName());

                carlist.add(imgid1);
                bmw[i] = imgid1;


            int imgid2 = getResources().getIdentifier("ford"+i,"drawable",getPackageName());

                carlist.add(imgid2);
                ford[i] =imgid2;


            int imgid3 = getResources().getIdentifier("fre"+i,"drawable",getPackageName());

                carlist.add(imgid3);
                fre[i] = imgid3;


            int imgid4 = getResources().getIdentifier("volk"+i,"drawable",getPackageName());

                carlist.add(imgid4);
                volk[i] = imgid4;


            int imgid5 = getResources().getIdentifier("jag"+i,"drawable",getPackageName());

                carlist.add(imgid5);
                jag[i] = imgid5;

        }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(carlist);
        System.out.println(Arrays.toString(bens));

        //getting the index of the arrayList randomly
        number = new Random().nextInt(carlist.size());

        //setting the carimages
        imageView.setImageResource(Integer.valueOf(carlist.get(number)));
        list_no = carlist.get(number);

        if(spinner!= null);{
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.carnames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        if(spinner!= null);{
            spinner.setAdapter(adapter);
        }

        if(switch_id.equals("true")) {
            startTimer();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if loop is used to change the submit button to next when submit is clicked
                if(no == 0){
                    //the timer pauses if the submit button is pressed
                    if(switch_id.equals("true")) {
                        countDownTimer.cancel();
                    }
                    if(name.equals("Benz")){
                        kkk=true;
                        for (int i = 0; i <bens.length ; i++) {
                            if (bens[i].equals(list_no)){
                                text.setText(R.string.correct);
                                i = bens.length;
                                kkk= false;


                            }

                        }if(kkk==true) {
                            text.setText(R.string.wrong);
                            loop();

                        }

                    }

                    if(name.equals("BMW")){
                        kkk=true;
                        for (int i = 0; i <bmw.length ; i++) {
                            if (bmw[i].equals(list_no)){
                                text.setText(R.string.correct);
                                i = bmw.length;
                                kkk=false;
                            }

                            }if(kkk==true) {
                            text.setText(R.string.wrong);
                            loop();

                        }

                    }

                    if(name.equals("Ferrari")){
                        kkk=true;
                        for (int i = 0; i <fre.length ; i++) {
                            if (fre[i].equals(list_no)){
                                text.setText(R.string.correct);
                                i = fre.length;
                                kkk=false;


                            }

                            }if(kkk==true){
                            text.setText(R.string.wrong);
                            loop();

                        }

                    }

                    if(name.equals("Jaguar")){
                        kkk=true;
                        for (int i = 0; i <jag.length ; i++) {
                            if (jag[i].equals(list_no)){
                                text.setText(R.string.correct);
                                i = jag.length;
                                kkk=false;


                            }

                            }if(kkk==true) {
                            text.setText(R.string.wrong);
                            loop();

                        }

                    }

                    if(name.equals("Volk")){
                        kkk=true;
                        for (int i = 0; i <volk.length ; i++) {
                            if (volk[i].equals(list_no)) {
                                text.setText(R.string.correct);
                                i = volk.length;
                                kkk = false;


                            }

                            }if(kkk==true) {
                        text.setText(R.string.wrong);
                        loop();

                        }

                    }

                    if(name.equals("Ford")){
                        kkk=true;
                        for (int i = 0; i <ford.length ; i++) {
                            if (ford[i].equals(list_no)){
                                text.setText(R.string.correct);
                                i = ford.length;
                                kkk=false;
                            }

                        }
                        if (kkk==true){
                            text.setText(R.string.wrong);
                            loop();

                        }

                    }
                    submit.setText(R.string.next);
                    no++;

                }else if(no == 1){
                    textView2.setText("");
                    number = new Random().nextInt(carlist.size());
                    imageView.setImageResource(Integer.valueOf(carlist.get(number)));
                    list_no = carlist.get(number);
                    text.setText("");
                    submit.setText(R.string.identify);

                    no = 0;
                    //it continues for the next picture
                    if(switch_id.equals("true")) {
                        timeMillisecs=20000;
                        startTimer();
                    }
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        name = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //this loop is used  case the answer is wrong get the Carimages of the displayed images
    public void loop(){
        for(int j = 0; j < bens.length; j++){
            if (bens[j].equals(list_no)) {
                textView2.setText(R.string.bens);
                j = bens.length;
            }
        }

        for(int j = 0; j < bmw.length; j++){
            if (bmw[j].equals(list_no)) {
                textView2.setText(R.string.bmw);
                j = bmw.length;
            }
        }

        for(int j = 0; j < fre.length; j++){
            if (fre[j].equals(list_no)) {
                textView2.setText(R.string.fre);
                j = fre.length;
            }
        }

        for(int j = 0; j < jag.length; j++){
            if (jag[j].equals(list_no)) {
                textView2.setText(R.string.jag);
                j = jag.length;
            }
        }

        for(int j = 0; j < ford.length; j++){
            if (ford[j].equals(list_no)) {
                textView2.setText(R.string.ford);
                j = ford.length;
            }
        }

        for(int j = 0; j < volk.length; j++){
            if (volk[j].equals(list_no)) {
                textView2.setText(R.string.volk);
                j = volk.length;
            }
        }


    }
    //timer is used make a countdown of 20 seconds
    //Reference frome Stackoverflow
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeMillisecs,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeMillisecs = millisUntilFinished;
                updateTimer();
            }

            @Override
            //if the timer is over automatically say if its wrong or correct answer
            public void onFinish() {
                if(name.equals("Benz")){
                    kkk=true;
                    for (int i = 0; i <bens.length ; i++) {
                        if (bens[i].equals(list_no)){
                            text.setText(R.string.correct);
                            i = bens.length;
                            kkk= false;


                        }

                    }if(kkk==true) {
                        text.setText(R.string.wrong);
                        loop();

                    }

                }

                if(name.equals("BMW")){
                    kkk=true;
                    for (int i = 0; i <bmw.length ; i++) {
                        if (bmw[i].equals(list_no)){
                            text.setText(R.string.correct);
                            i = bmw.length;
                            kkk=false;


                        }

                    }if(kkk==true) {
                        text.setText(R.string.wrong);
                        loop();

                    }

                }

                if(name.equals("Ferrari")){
                    kkk=true;
                    for (int i = 0; i <fre.length ; i++) {
                        if (fre[i].equals(list_no)){
                            text.setText(R.string.correct);
                            i = fre.length;
                            kkk=false;


                        }

                    }if(kkk==true){
                        text.setText(R.string.wrong);
                        loop();

                    }

                }

                if(name.equals("Jaguar")){
                    kkk=true;
                    for (int i = 0; i <jag.length ; i++) {
                        if (jag[i].equals(list_no)){
                            text.setText(R.string.correct);
                            i = jag.length;
                            kkk=false;


                        }

                    }if(kkk==true) {
                        text.setText(R.string.wrong);
                        loop();

                    }

                }

                if(name.equals("Volk")){
                    kkk=true;
                    for (int i = 0; i <volk.length ; i++) {
                        if (volk[i].equals(list_no)) {
                            text.setText(R.string.correct);
                            i = volk.length;
                            kkk = false;


                        }

                    }if(kkk==true) {
                        text.setText(R.string.wrong);
                        loop();

                    }

                }

                if(name.equals("Ford")){
                    kkk=true;
                    for (int i = 0; i <ford.length ; i++) {
                        if (ford[i].equals(list_no)){
                            text.setText(R.string.correct);
                            i = ford.length;
                            kkk=false;
                        }

                    }
                    if (kkk==true){
                        text.setText(R.string.wrong);
                        loop();

                    }

                }
                submit.setText(R.string.next);
                no++;
            }
        }.start();

    }

    //to update the countdown
    public void updateTimer(){
        int seconds = (int) timeMillisecs % 60000/1000;
        String timeLeftText;
        timeLeftText="";
        timeLeftText += seconds;

        text_timer.setText("Remaining Time : "+timeLeftText);
    }
}
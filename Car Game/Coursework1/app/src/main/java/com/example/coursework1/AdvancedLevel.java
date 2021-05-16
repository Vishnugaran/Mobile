package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class AdvancedLevel extends AppCompatActivity {
    boolean correct,correct2,correct3;
    TextView text1,text2,text3,points,answer,text_timer;
    EditText edit1,edit2,edit3;
    ImageView imageView,imageView2,imageView3;
    Button btn1;
    int rand1,rand2,rand3,chance=0,random,random1,random2,pointsNo=0,no=0;
    ArrayList<Integer> bens;
    ArrayList<Integer> bmw;
    ArrayList<Integer> ford;
    ArrayList<Integer> fre;
    ArrayList<Integer> volk;
    ArrayList<Integer> jag;

    int getRandom;
    int getRandom1;
    int getRandom2;
    ArrayList<Integer> carlist;
    ArrayList<ArrayList<Integer> > carObjects;
    String name,name2,name3;
    String switch_id;
    private long timeMillisecs = 20000;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Phone Full Scree view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_advanced_level);
        btn1 = (Button)findViewById(R.id.adbutton);
        text1 = (TextView)findViewById(R.id.adans1);
        answer = (TextView)findViewById(R.id.admainans);
        text2 = (TextView)findViewById(R.id.adans2);
        text3 = (TextView)findViewById(R.id.adans3);
        text_timer = (TextView)findViewById(R.id.advancedtimer);
        points = (TextView)findViewById(R.id.adpoints);
        edit1 = (EditText) findViewById(R.id.adtxt1);
        edit2 = (EditText)findViewById(R.id.adtxt2);
        edit3 = (EditText)findViewById(R.id.adtxt3);
        carObjects = new ArrayList<>();
        carlist = new ArrayList<>();
        bens = new ArrayList<>();
        bmw = new ArrayList<>();
        fre = new ArrayList<>();
        jag = new ArrayList<>();
        ford = new ArrayList<>();
        volk = new ArrayList<>();
        imageView=(ImageView)findViewById(R.id.adimg1);
        imageView2=(ImageView)findViewById(R.id.adimg2);
        imageView3=(ImageView)findViewById(R.id.adimg3);
        switch_id = getIntent().getStringExtra("Switch");

        //for loops are used to retrieve the images from drawable folder and store it to array list and arrays
        for (int i = 0; i < 5; i++) {
            try {
                int imgid = getResources().getIdentifier("bens"+i,"drawable",getPackageName());
                carlist.add(imgid);
                bens.add(imgid);


                int imgid1 = getResources().getIdentifier("bmw"+i,"drawable",getPackageName());

                carlist.add(imgid1);
                bmw.add(imgid1);


                int imgid2 = getResources().getIdentifier("ford"+i,"drawable",getPackageName());

                carlist.add(imgid2);
               ford.add(imgid2);


                int imgid3 = getResources().getIdentifier("fre"+i,"drawable",getPackageName());

                carlist.add(imgid3);
                fre.add(imgid3);


                int imgid4 = getResources().getIdentifier("volk"+i,"drawable",getPackageName());

                carlist.add(imgid4);
                volk.add(imgid4);


                int imgid5 = getResources().getIdentifier("jag"+i,"drawable",getPackageName());

                carlist.add(imgid5);
                jag.add(imgid5);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        carObjects.add(bens);
        carObjects.add(bmw);
        carObjects.add(ford);
        carObjects.add(fre);
        carObjects.add(jag);
        carObjects.add(volk);
        //getting the index of the arrayList randomly
        for (int i=0;i<1;){
            random = new Random().nextInt(6);
            random1 = new Random().nextInt(6);
            random2 = new Random().nextInt(6);
            if((random != random1) && (random1 != random2) && (random != random2)){
                rand1 = new Random().nextInt(5);
                rand2 = new Random().nextInt(5);
                rand3 = new Random().nextInt(5);

                System.out.println(rand1+" "+rand2+" "+rand3);
                getRandom = carObjects.get(random).get(rand1);
                getRandom1 = carObjects.get(random1).get(rand2);
                getRandom2 = carObjects.get(random2).get(rand3);

                System.out.println(getRandom);
                System.out.println(getRandom1);
                System.out.println(getRandom2);
                System.out.println("yes");
                imageView.setImageResource(getRandom);
                imageView2.setImageResource(getRandom1);
                imageView3.setImageResource(getRandom2);
                i = 1;
            }
        }

        loop(getRandom,getRandom1,getRandom2);
        if(switch_id.equals("true")) {
            startTimer();
        }


        btn1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //if loop is used to change the submit button to next when submit is clicked
                if (no == 0) {
                    //the timer pauses if the submit button is pressed
                    if(switch_id.equals("true")) {
                        countDownTimer.cancel();
                    }

                    if (chance <2) {
                        if (name.equals(edit1.getText().toString().toUpperCase()) && correct==false) {
                            edit1.setBackgroundColor(Color.GREEN);
                            edit1.setEnabled(false);
                            correct = true;
                            pointsNo++;
                        } else if(correct==false) {
                            correct = false;
                           edit1.setBackgroundColor(Color.RED);
                        }

                        if (name2.equals(edit2.getText().toString().toUpperCase()) && correct2==false) {
                            edit2.setBackgroundColor(Color.GREEN);
                            edit2.setEnabled(false);
                            correct2 = true;
                            pointsNo++;
                        } else if(correct2==false) {
                            correct2 = false;
                            edit2.setBackgroundColor(Color.RED);
                        }

                        if (name3.equals(edit3.getText().toString().toUpperCase()) && correct3==false) {
                            edit3.setBackgroundColor(Color.GREEN);
                            edit3.setEnabled(false);
                            correct3 = true;
                            pointsNo++;
                        } else if(correct3==false) {
                            correct3 = false;
                            edit3.setBackgroundColor(Color.RED);
                        }
                        System.out.println("points "+pointsNo);
                        //save = save + pointsNo;
                        points.setText(String.valueOf("Points : "+pointsNo));
                        if (correct3 == true && correct == true && correct2 == true) {
                            answer.setText(R.string.correct);
                            btn1.setText(R.string.next);
                            no=1;
                        } else {
                            chance++;
                        }
                    } else {
                        answer.setText(R.string.wrong);
                        if (correct == false) {
                            text1.setText(name);
                            text1.setBackgroundColor(Color.BLACK);
                        }
                        if (correct2 == false) {
                            text2.setText(name2);
                            text2.setBackgroundColor(Color.BLACK);
                        }
                        if (correct3 == false) {
                            text3.setText(name3);
                            text3.setBackgroundColor(Color.BLACK);
                        }
                        btn1.setText(R.string.next);
                        no=1;
                    }
                }else if(no==1){

                    for (int i=0;i<1;){
                        random = new Random().nextInt(6);
                        random1 = new Random().nextInt(6);
                        random2 = new Random().nextInt(6);
                        System.out.println("helll");
                        if((random != random1) && (random1 != random2) && (random != random2)){
                            rand1 = new Random().nextInt(5);
                            rand2 = new Random().nextInt(5);
                            rand3 = new Random().nextInt(5);

                            System.out.println(rand1+" "+rand2+" "+rand3);
                            getRandom = carObjects.get(random).get(rand1);
                            getRandom1 = carObjects.get(random1).get(rand2);
                            getRandom2 = carObjects.get(random2).get(rand3);

                            System.out.println(getRandom);
                            System.out.println(getRandom1);
                            System.out.println(getRandom2);
                            System.out.println("yes");
                            imageView.setImageResource(getRandom);
                            imageView2.setImageResource(getRandom1);
                            imageView3.setImageResource(getRandom2);
                            i = 1;
                        }
                    }
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    edit1.setText("");
                    edit2.setText("");
                    edit3.setText("");
                    answer.setText("");
                    text1.setBackgroundColor(Color.WHITE);
                    text2.setBackgroundColor(Color.WHITE);
                    text3.setBackgroundColor(Color.WHITE);
                    edit1.setEnabled(true);
                    edit2.setEnabled(true);
                    edit3.setEnabled(true);
                    edit1.setBackgroundColor(Color.WHITE);
                    edit2.setBackgroundColor(Color.WHITE);
                    edit3.setBackgroundColor(Color.WHITE);
                    correct3=false;
                    correct=false;
                    correct2=false;
                    loop(getRandom,getRandom1,getRandom2);
                    no=0;
                    chance=0;
                    btn1.setText("Submit");
                    if(switch_id.equals("true")) {
                        timeMillisecs=20000;
                        startTimer();
                    }
                }
            }
        });


    }




    //this loop is used  case the answer is wrong get the Carimages of the displayed carnames
    public void loop(int x1,int x2,int x3){
        for (int i = 0; i < bens.size() ; i++) {
            if (bens.get(i).equals(x1)) {
               name = "BENZ";
            }
            if (bens.get(i).equals(x2)) {
                name2 = "BENZ";
            }
            if (bens.get(i).equals(x3)) {
                name3 = "BENZ";
            }

        }

        for (int i = 0; i < bmw.size() ; i++) {
            if (bmw.get(i).equals(x1)) {
                name = "BMW";
            }
            if (bmw.get(i).equals(x2)) {
                name2 = "BMW";
            }
            if (bmw.get(i).equals(x3)) {
                name3 = "BMW";
            }

        }

        for (int i = 0; i < fre.size() ; i++) {
            if (fre.get(i).equals(x1)) {
                name = "FERRARI";
            }
            if (fre.get(i).equals(x2)) {
                name2 = "FERRARI";
            }
            if (fre.get(i).equals(x3)) {
                name3 = "FERRARI";
            }

        }

        for (int i = 0; i < jag.size() ; i++) {
            if (jag.get(i).equals(x1)) {
                name = "JAGUAR";
            }
            if (jag.get(i).equals(x2)) {
                name2 = "JAGUAR";
            }
            if (jag.get(i).equals(x3)) {
                name3 = "JAGUAR";
            }

        }

        for (int i = 0; i < volk.size() ; i++) {
            if (volk.get(i).equals(x1)) {
                name = "VOLK";
            }
            if (volk.get(i).equals(x2)) {
                name2 = "VOLK";
            }
            if (volk.get(i).equals(x3)) {
                name3 = "VOLK";
            }

        }

        for (int i = 0; i < ford.size() ; i++) {
            if (ford.get(i).equals(x1)) {
                name = "FORD";
            }
            if (ford.get(i).equals(x2)) {
                name2 = "FORD";
            }
            if (ford.get(i).equals(x3)) {
                name3 = "FORD";
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
                if (name.equals(edit1.getText().toString().toUpperCase()) && correct == false) {
                    edit1.setBackgroundColor(Color.GREEN);
                    edit1.setEnabled(false);
                    correct = true;
                    pointsNo++;
                } else if (correct == false) {
                    correct = false;
                    edit1.setBackgroundColor(Color.RED);
                }

                if (name2.equals(edit2.getText().toString().toUpperCase()) && correct2 == false) {
                    edit2.setBackgroundColor(Color.GREEN);
                    edit2.setEnabled(false);
                    correct2 = true;
                    pointsNo++;
                } else if (correct2 == false) {
                    correct2 = false;
                    edit2.setBackgroundColor(Color.RED);
                }

                if (name3.equals(edit3.getText().toString().toUpperCase()) && correct3 == false) {
                    edit3.setBackgroundColor(Color.GREEN);
                    edit3.setEnabled(false);
                    correct3 = true;
                    pointsNo++;
                } else if (correct3 == false) {
                    correct3 = false;
                    edit3.setBackgroundColor(Color.RED);
                }
                System.out.println("points " + pointsNo);
                //save = save + pointsNo;
                points.setText(String.valueOf("Points : " + pointsNo));
                if (correct3 == true && correct == true && correct2 == true) {
                    answer.setText(R.string.correct);

                } else {
                    answer.setText(R.string.wrong);
                    if (correct == false) {
                        text1.setText(name);
                        text1.setBackgroundColor(Color.BLACK);
                    }
                    if (correct2 == false) {
                        text2.setText(name2);
                        text2.setBackgroundColor(Color.BLACK);
                    }
                    if (correct3 == false) {
                        text3.setText(name3);
                        text3.setBackgroundColor(Color.BLACK);
                    }


                    btn1.setText(R.string.next);
                    no = 1;

                }


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


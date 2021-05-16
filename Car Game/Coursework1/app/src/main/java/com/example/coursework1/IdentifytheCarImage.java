
package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class IdentifytheCarImage extends AppCompatActivity {
    TextView text,textView2,text_timer;
    ImageView imageView,imageView2,imageView3;
    Button btn1;
    int rand1,rand2,rand3,chance=0;
    Object[] bens;
    Object[] bmw;
    Object[] ford;
    Object[] fre;
    Object[] volk;
    Object[] jag;

    int getRandom;
    int getRandom1;
    int getRandom2;
    ArrayList<Integer> carlist;

    String name;
    String switch_id;
    private long timeMillisecs = 20000;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Phone Full Scree view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_identifythe_car_image);
        btn1 = (Button)findViewById(R.id.clickbtn);
        text = (TextView)findViewById(R.id.nametext);
        textView2 = (TextView)findViewById(R.id.answertext);
        text_timer = (TextView)findViewById(R.id.imageTimer);
        carlist = new ArrayList<>();
        bens = new Object[5];
        bmw = new Object[5];
        fre = new Object[5];
        jag = new Object[5];
        ford = new Object[5];
        volk = new Object[5];
        imageView=(ImageView)findViewById(R.id.randomimg1);
        imageView2=(ImageView)findViewById(R.id.randomimg2);
        imageView3=(ImageView)findViewById(R.id.randomimg3);
        switch_id = getIntent().getStringExtra("Switch");
        switch_id = getIntent().getStringExtra("Switch");

//for loops are used to retrieve the images from drawable folder and store it to array list and arrays
        for (int i = 0; i < 5; i++) {
            try {


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
        //getting the index of the arrayList randomly
        rand1 = new Random().nextInt(carlist.size());
        rand2 = new Random().nextInt(carlist.size());
        rand3 = new Random().nextInt(carlist.size());

        getRandom = carlist.get(rand1);
        getRandom1 = carlist.get(rand2);
        getRandom2 = carlist.get(rand3);

        //for loop is used to run the process till the three images displayed are different
        for (int i = 0; i < 1; i++) {

            //if condition used to make sure that all three images are different
            if ((getRandom != getRandom1) && (getRandom1 != getRandom2) && (getRandom != getRandom2)) {

                        imageView.setImageResource(carlist.get(rand1));
                        imageView2.setImageResource(carlist.get(rand2));
                        imageView3.setImageResource(carlist.get(rand3));
                        i = 1;

                }



            }
        loop(getRandom,getRandom1,getRandom2);

        if(switch_id.equals("true")) {
            startTimer();
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_id.equals("true")) {
                    countDownTimer.cancel();
                }
                rand1 = new Random().nextInt(carlist.size());
                rand2 = new Random().nextInt(carlist.size());
                rand3 = new Random().nextInt(carlist.size());

                getRandom = carlist.get(rand1);
                getRandom1 = carlist.get(rand2);
                getRandom2 = carlist.get(rand3);


                //for loop is used to run the process till the three images displayed are different
                for (int i = 0; i < 1; i++) {

                    //if condition used to make sure that all three images are different
                    if ((getRandom != getRandom1) && (getRandom1 != getRandom2) && (getRandom != getRandom2)) {

                        imageView.setImageResource(carlist.get(rand1));
                        imageView2.setImageResource(carlist.get(rand2));
                        imageView3.setImageResource(carlist.get(rand3));
                        i = 1;



                    }





                }
                loop(getRandom,getRandom1,getRandom2);
                image(v);
                image1(v);
                image2(v);
                chance=0;
                textView2.setText("");
                if(switch_id.equals("true")) {
                    timeMillisecs = 20000;
                    startTimer();
                }
            }

        });


        }



    //random display
    @SuppressLint("SetTextI18n")
    public void loop(int x1, int x2, int x3){
        for (int i = 0; i < bens.length ; i++) {
            if (bens[i].equals(x1) || bens[i].equals(x2) ||bens[i].equals(x3)) {
                text.setText("Benz");
                i = bens.length;
            }

        }

        for (int i = 0; i < bmw.length ; i++) {
            if (bmw[i].equals(x1) || bmw[i].equals(x2) ||bmw[i].equals(x3)) {
                text.setText("bmw");
                i = bmw.length;
            }

        }

        for (int i = 0; i < ford.length ; i++) {
            if (ford[i].equals(x1) || ford[i].equals(x2) ||ford[i].equals(x3)) {
                text.setText("Ford");
                i = ford.length;
            }

        }

        for (int i = 0; i < fre.length ; i++) {
            if (fre[i].equals(x1) || fre[i].equals(x2) ||fre[i].equals(x3)) {
                text.setText("Ferrari");
                i = fre.length;
            }

        }

        for (int i = 0; i < jag.length ; i++) {
            if (jag[i].equals(x1) || jag[i].equals(x2) ||jag[i].equals(x3)) {
                text.setText("Jaguar");
                i = jag.length;
            }

        }

        for (int i = 0; i < volk.length ; i++) {
            if (volk[i].equals(x1) || volk[i].equals(x2) ||volk[i].equals(x3)) {
                text.setText("Volk");
                i = volk.length;
            }

        }

    }

    public void image(View view){
        if(chance==0){
            String name1 = text.getText().toString();
            if(name1.equals("Benz")){
                for (int i = 0; i <5 ; i++) {
                    if(bens[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ferrari")){
                for (int i = 0; i <5 ; i++) {
                    if(fre[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("BMW")){
                for (int i = 0; i <5 ; i++) {
                    if(bmw[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Jaguar")){
                for (int i = 0; i <5 ; i++) {
                    if(jag[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Volk")){
                for (int i = 0; i <5 ; i++) {
                    if(volk[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ford")){
                for (int i = 0; i <5 ; i++) {
                    if(ford[i].equals(getRandom)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
        }
        chance++;
    }

    public void image1(View view){
        if(chance==0){
            String name1 = text.getText().toString();
            if(name1.equals("Benz")){
                for (int i = 0; i <5 ; i++) {
                    if(bens[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ferrari")){
                for (int i = 0; i <5 ; i++) {
                    if(fre[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("BMW")){
                for (int i = 0; i <5 ; i++) {
                    if(bmw[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Jaguar")){
                for (int i = 0; i <5 ; i++) {
                    if(jag[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Volk")){
                for (int i = 0; i <5 ; i++) {
                    if(volk[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ford")){
                for (int i = 0; i <5 ; i++) {
                    if(ford[i].equals(getRandom1)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
        }
        chance++;
    }
    public void image2(View view){
        if(chance==0){
            String name1 = text.getText().toString();
            if(name1.equals("Benz")){
                for (int i = 0; i <5 ; i++) {
                    if(bens[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ferrari")){
                for (int i = 0; i <5 ; i++) {
                    if(fre[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("BMW")){
                for (int i = 0; i <5 ; i++) {
                    if(bmw[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Jaguar")){
                for (int i = 0; i <5 ; i++) {
                    if(jag[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Volk")){
                for (int i = 0; i <5 ; i++) {
                    if(volk[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
            if(name1.equals("Ford")){
                for (int i = 0; i <5 ; i++) {
                    if(ford[i].equals(getRandom2)){
                        textView2.setText(R.string.correct);
                        i=5;
                    }else{
                        textView2.setText(R.string.wrong);
                    }
                }
            }
        }
        chance++;

    }
    //timer is used make a countdown of 20 seconds
    //Reference frome Stackoverflow
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeMillisecs,1000) {
            @Override
            //if the timer is over automatically say if its wrong or correct answer
            public void onTick(long millisUntilFinished) {
                timeMillisecs = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                textView2.setText(R.string.wrong);

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




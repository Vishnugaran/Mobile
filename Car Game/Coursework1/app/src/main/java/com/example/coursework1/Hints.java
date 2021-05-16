package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hints extends AppCompatActivity {
    TextView text,textView2,textView3,text_timer;
    Object[] bens;
    Object[] bmw;
    Object[] ford;
    Object[] fre;
    Object[] volk;
    Object[] jag;
    ArrayList<Integer> carlist;
    ArrayList<String> dashesList;
    Random random;
    ImageView imageView;
    Spinner spinner;
    Button submit;
    int number,list_no,no=0,chances=0,chancesNo=0;
    String name,a="";
    char[] letters;
    String[] dashes;
    EditText editText;
    boolean guess;
    String switch_id;
    private long timeMillisecs = 20000;
    CountDownTimer countDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Phone Full Scree view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hints);
        text = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.answerid);
        textView3 = (TextView)findViewById(R.id.chanceid);
        text_timer = (TextView)findViewById(R.id.Hinttimer);
        carlist = new ArrayList<>();
        dashesList = new ArrayList<>();
        random = new Random();
        bens = new Object[5];
        bmw = new Object[5];
        fre = new Object[5];
        jag = new Object[5];
        ford = new Object[5];
        volk = new Object[5];
        imageView =(ImageView)findViewById(R.id.imagevid);
        spinner =(Spinner) findViewById(R.id.spinnerid);
        submit =(Button)findViewById(R.id.hintButton);
        editText = (EditText)findViewById(R.id.edittxt);
        switch_id = getIntent().getStringExtra("Switch");
//        carArray = new Integer[]{R.drawable.bens1,R.drawable.bens2,R.drawable.bens3,R.drawable.bens4,R.drawable.bens5,R.drawable.bmw1,R.drawable.bmw2,R.drawable.bmw3,R.drawable.bmw4,R.drawable.bmw5,R.drawable.ford1,R.drawable.ford2,R.drawable.ford3,R.drawable.ford4,R.drawable.ford5,R.drawable.fre1,R.drawable.fre2,R.drawable.fre3,R.drawable.fre4,R.drawable.fre5,R.drawable.jag1,R.drawable.jag2,R.drawable.jag3,R.drawable.jag4,R.drawable.jag5,R.drawable.volk1,R.drawable.volk2,R.drawable.volk3,R.drawable.volk4,R.drawable.volk5};

        //for loops are used to retrieve the images from drawable folder and store it to array list and arrays
        for (int i = 0; i < 5; i++) {
            try {
                int imgid = getResources().getIdentifier("bens"+i,"drawable",getPackageName());
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
        number = new Random().nextInt(carlist.size());

        imageView.setImageResource(Integer.valueOf(carlist.get(number)));
        list_no = carlist.get(number);

        loop(list_no);
        System.out.println(name);
        letters = name.toCharArray();
        System.out.println(Arrays.toString(letters));
        System.out.println(letters.length);

        for (int i = 0; i <name.length(); i++) {
            //dashes[i] = "_";
            dashesList.add("_");
        }
        text.setText(dashesList.toString());
        //System.out.println(dashes);
        if(switch_id.equals("true")) {
            startTimer();
        }

            submit.setOnClickListener(new View.OnClickListener(){
                @Override
                //if loop is used to change the submit button to next when submit is clicked
                public void onClick(View v) {
                    //the timer pauses if the submit button is pressed
                    if(switch_id.equals("true")) {
                        countDownTimer.cancel();
                    }
                    if (chances<2) {

                    guess = false;
                    if (no == 0) {
                        String edit = editText.getText().toString();
                        if (edit != null) {

                            for (int i = 0; i < letters.length; i++) {
                                //String s = String.valueOf(letters[i]);
                                //.out.println(s);
                                if (edit.toUpperCase().equals(String.valueOf(letters[i]))) {
                                    dashesList.set(i, edit);
                                    text.setText(dashesList.toString());
                                    guess = true;

                                }
                            }
                            if (guess == false) {
                                chances++;
                                if(switch_id.equals("true")) {
                                    timeMillisecs = 20000;
                                    startTimer();
                                }
                                textView3.setText(String.valueOf(chances));
                                System.out.println("chances " + chances);
                            }
                        }


                        System.out.println(dashesList.toString() + " " + Arrays.toString(letters));
                        a = "";
                        for (int i = 0; i < dashesList.size(); i++) {
                            a = a + dashesList.get(i);
                        }
                        System.out.println(a.toUpperCase().equals(name));
                        System.out.println(a + " " + name);
                        if (a.toUpperCase().equals(name)) {
                            text.setText(R.string.correct);
                            editText.setText("");
                            dashesList.clear();
                            submit.setText(R.string.next);
                            no = 1;
                        }

                    } else if (no == 1) {
                        number = new Random().nextInt(carlist.size());

                        imageView.setImageResource(Integer.valueOf(carlist.get(number)));
                        list_no = carlist.get(number);
                        loop(list_no);
                        letters = name.toCharArray();
                        submit.setText(R.string.identify);
                        text.setText("");
                        textView2.setText("");
                        editText.setText("");

                        for (int i = 0; i < name.length(); i++) {
                            //dashes[i] = "_";
                            dashesList.add("_");
                        }
                        text.setText(dashesList.toString());
                        no = 0;
                        //it continues for the next picture
                        if(switch_id.equals("true")) {
                            timeMillisecs=20000;
                            startTimer();
                        }
                    }
                    }else{

                        editText.setText("");
                        text.setText(R.string.wrong);
                        textView2.setText(name);
                        submit.setText(R.string.next);
                        chances=0;
                        dashesList.clear();
                        no=1;

                    }

                }

            });




    }
    //this loop is used  case the answer is wrong get the Carimages of the displayed images
    public void loop(int id){
        for (int i=0;i<5;i++){
            if(bens[i].equals(id)){
                name = "BENZ";
            }
            if(bmw[i].equals(id)){
                name = "BMW";
            }
            if(fre[i].equals(id)){
                name = "FERRARI";
            }
            if(jag[i].equals(id)){
                name = "JAGUAR";
            }
            if(ford[i].equals(id)){
                name = "FORD";
            }
            if(volk[i].equals(id)){
                name = "VOLKSWAGEN";
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
                    if (chances==2) {
                        if (a.toUpperCase().equals(name)) {
                            text.setText(R.string.correct);
                            editText.setText("");
                            dashesList.clear();
                            submit.setText(R.string.next);
                            no = 1;
                        }


                    }else{
                        editText.setText("");
                        text.setText(R.string.wrong);
                        textView2.setText(name);
                        submit.setText(R.string.next);
                        chances=0;
                        dashesList.clear();
                        no=1;
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
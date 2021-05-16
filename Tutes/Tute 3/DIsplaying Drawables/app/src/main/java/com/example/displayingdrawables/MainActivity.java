package com.example.displayingdrawables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] images = { R.drawable.brittany_02625, R.drawable.welsh_springer_spaniel_08203 };
    int counter = 0;
    private ImageView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.im_view);
        // v1.setImageResource(R.drawable.brittany_02625);


        //The getIdentifier() method accepts 3 arguments: the first is the resource name, the second the type of the resource (it is a drawable) and the third is the package name.
        String resource = "brittany_02625";
        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.displayingdrawables");
        v1.setImageResource(resource_id);
    }

    public void onClick(View view) {
        if (counter == 0) {
            counter = counter + 1;
            v1.setImageResource(images[counter]);
        }
        else if (counter == 1) {
            counter = counter - 1;
            v1.setImageResource(images[counter]);
        }

    }
}
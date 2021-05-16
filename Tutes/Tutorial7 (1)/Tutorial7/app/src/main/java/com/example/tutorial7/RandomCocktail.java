package com.example.tutorial7;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RandomCocktail extends Activity {
    final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    ImageView imageView;
    TextView textView;
    private URL requestURL;
    private HttpURLConnection connection;
    private String resultString;
    private JSONObject object;
    String preparationMethod;
    String imageLinkString;
    Bitmap bitmap;
    TextView cocktailNameTxt;
    String cocktailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_cocktail);
        textView=findViewById(R.id.preparation_method);
        imageView=findViewById(R.id.random_cocktail_image);
        cocktailNameTxt=findViewById(R.id.cocktail_name);

//        DisplayMetrics dm=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
//
//        int width=dm.widthPixels;
//        int height=dm.heightPixels;
//
//        getWindow().setLayout((int)(width*.9),(int)(height*.7));
//
        initialize();

    }

    public void initialize() {


        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
                Log.d("MainActivity", String.valueOf(builtURI));
                try {
                    requestURL = new URL(builtURI.toString());
                    //System.out.println(requestURL);
                    downloadUrl(String.valueOf(requestURL));
                } catch (MalformedURLException e) {
                } catch (IOException e) {
                }
                try {
                    InputStream inputStream = new URL(imageLinkString).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                           textView.setText(preparationMethod);
                           imageView.setImageBitmap(bitmap);
                           cocktailNameTxt.setText(cocktailName);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        backgroundThread.start();
    }

    private String downloadUrl(String url) throws IOException {
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            // Start the query
            connection.connect();
            int response = connection.getResponseCode();
            inputStream = connection.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = convertInputToString(inputStream);
            return contentAsString;

            // Close the InputStream and connection
        } finally {
            connection.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public String convertInputToString(InputStream inputStream) throws IOException, UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        if (builder.length() == 0) {
            return null;
        }
        resultString = builder.toString();
        JSONObject data = null;
        try {
            data = new JSONObject(resultString);
            JSONArray drink = (JSONArray) data.get("drinks");
            if (drink != null) {

                object = (JSONObject) drink.get(0);
                preparationMethod = (object.get("strInstructions")).toString();
                cocktailName=(object.get("strDrink")).toString();
                Log.d("MainActivity", preparationMethod);
                imageLinkString = object.get("strDrinkThumb").toString();




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    }

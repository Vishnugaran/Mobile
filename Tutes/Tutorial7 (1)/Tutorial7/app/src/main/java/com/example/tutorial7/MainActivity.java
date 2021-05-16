package com.example.tutorial7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?";
    final String BASE_ID_URL = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?";
    final String INGREDIENT_URL = "i";
    final String COCKTAIL_NAME_URL = "s";
    String preparationMethod = "s";
    private Spinner ingredientSpinner;
    private URL requestURL;
    private URL requestByNameURL;
    private HttpURLConnection connection;
    private HttpURLConnection connectionByName;
    private String resultString;
    private String resultStringByName;
    private JSONObject cocktailObject;
    private JSONObject object;
    private JSONObject selectedCocktailObject;
    private String cocktailName;
    Spinner cocktailsAvailableSpinner;
    List<String> ingredientsList;
    List<String> cocktailsAvailableList;
    String ingredientSelected;
    private Button search;
    String selectedCocktail;
    private Button displayDetails;
    JSONArray drinks;
    int num;
    ImageView cocktailImage;
    String imageLinkString;
    Bitmap bitmap;
    String cocktailId;
    TextView preparationTxt;
    Button randomCocktail;
    TextView methodLabelTxt;
    TextView cocktailAvailableLabel;
    TextView cocktailNameMain;
    String cocktailNameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingredientSpinner = findViewById(R.id.spinner_ingredient);
        cocktailsAvailableSpinner = findViewById(R.id.cocktails_available_spinner);
        search = findViewById(R.id.search_button);
        displayDetails = findViewById(R.id.display_details_button);
        preparationTxt=findViewById(R.id.preparation_txt);
        randomCocktail=findViewById(R.id.random_cocktail_button);
        methodLabelTxt=findViewById(R.id.method_label);
        cocktailAvailableLabel=findViewById(R.id.cocktails_available);
        cocktailNameMain=findViewById(R.id.cocktail_name_main);
        initialize();

        randomCocktail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this,RandomCocktail.class));
            }
        });

    }

    void initialize() {
        displayDetails.setEnabled(false);
        displayDetails.setVisibility(View.INVISIBLE);
        cocktailsAvailableSpinner.setVisibility(View.INVISIBLE);
        cocktailsAvailableSpinner.setEnabled(false);
        methodLabelTxt.setVisibility(View.INVISIBLE);
        cocktailAvailableLabel.setVisibility(View.INVISIBLE);
        cocktailNameMain.setVisibility(View.INVISIBLE);
        ingredientsList = new ArrayList<>();
        ingredientsList.add("Vodka");
        ingredientsList.add("Gin");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredientSpinner.setAdapter(adapter);
        cocktailImage = findViewById(R.id.cocktail_image);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchByIngredient();
                cocktailAvailableLabel.setVisibility(View.VISIBLE);

                cocktailsAvailableSpinner.setEnabled(true);
                displayDetails.setVisibility(View.VISIBLE);
                cocktailsAvailableSpinner.setVisibility(View.VISIBLE);
                if (!displayDetails.isEnabled()) {
                    displayDetails.setEnabled(true);

                }
            }
        });

        displayDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodLabelTxt.setVisibility(View.VISIBLE);
                cocktailNameMain.setVisibility(View.VISIBLE);
//                SearchByName();
                try {
                    displayImageAndPreparationMethod();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void SearchByIngredient() {
        ingredientSelected = (String) ingredientSpinner.getSelectedItem();

        Thread backgroundThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(INGREDIENT_URL, ingredientSelected)
                        .build();
                Log.d("MainActivity", String.valueOf(builtURI));
                try {
                    requestURL = new URL(builtURI.toString());
                    //System.out.println(requestURL);
                    downloadUrl(String.valueOf(requestURL));
                } catch (MalformedURLException e) {
                } catch (IOException e) {
                }
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addResultToSpinner();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        backgroundThread1.start();


    }

    private String downloadByNameUrl(String url) throws IOException {
        InputStream inputStream = null;
        try {
            connectionByName = (HttpURLConnection) requestByNameURL.openConnection();
            connectionByName.setReadTimeout(10000 /* milliseconds */);
            connectionByName.setConnectTimeout(15000 /* milliseconds */);
            // Start the query
            connectionByName.connect();
            int response = connectionByName.getResponseCode();
            inputStream = connectionByName.getInputStream();

            // Convert the InputStream into a string
            String contentAsString1 = convertInputToStringByName(inputStream);
            return contentAsString1;

            // Close the InputStream and connection
        } finally {
            connectionByName.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }
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

    private String convertInputToStringByName(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        if (builder.length() == 0) {
            return null;
        }
        resultStringByName = builder.toString();
        Log.d("MainActivity", resultStringByName);
        JSONObject data = null;
        try {
            data = new JSONObject(resultStringByName);
            JSONArray drink = (JSONArray) data.get("drinks");
            if (drink != null) {

                object = (JSONObject) drink.get(0);
                cocktailNameSelected=(object.get("strDrink")).toString();
                preparationMethod = (object.get("strInstructions")).toString();
                Log.d("MainActivity", preparationMethod);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    void displayImageAndPreparationMethod() throws JSONException {
        selectedCocktailObject = (JSONObject) drinks.get(num);
        imageLinkString = selectedCocktailObject.get("strDrinkThumb").toString();
        cocktailId = selectedCocktailObject.get("idDrink").toString();

//        Log.d("MainActivity", imageLinkString);
//        Log.d("MainActivity", String.valueOf(cocktailId));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Uri builtNameURI = Uri.parse(BASE_ID_URL).buildUpon()
                        .appendQueryParameter(INGREDIENT_URL, cocktailId)
                        .build();
                Log.d("MainActivity", String.valueOf(builtNameURI));
                try {
                    requestByNameURL = new URL(builtNameURI.toString());
                    downloadByNameUrl(String.valueOf(requestByNameURL));

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
                            cocktailImage.setImageBitmap(bitmap);
                            preparationTxt.setText(preparationMethod);
                            cocktailNameMain.setText(cocktailNameSelected);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
        thread.start();

    }


    private String convertInputToString(InputStream inputStream) throws IOException {
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
        Log.d("MainActivity", resultString);
        JSONObject data = null;
        try {
            data = new JSONObject(resultString);
            cocktailsAvailableList = new ArrayList<>();
            drinks = (JSONArray) data.get("drinks");
            if (drinks != null) {
                for (int i = 0; i < drinks.length(); i++) {
                    cocktailObject = (JSONObject) drinks.get(i);
                    cocktailName = (cocktailObject.get("strDrink")).toString();
                    cocktailsAvailableList.add(cocktailName);
                }
            }

            for (int i = 0; i < cocktailsAvailableList.size(); i++) {
                Log.d("MainActivity", String.valueOf(cocktailsAvailableList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    void addResultToSpinner() {
        ArrayAdapter<String> adapterCocktail = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cocktailsAvailableList);
        adapterCocktail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cocktailsAvailableSpinner.setAdapter(adapterCocktail);
        selectedCocktail = (String) cocktailsAvailableSpinner.getSelectedItem();
        cocktailsAvailableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                num = position;
                Log.d("MainActivity", String.valueOf(num));
                try {
                    selectedCocktailObject = (JSONObject) drinks.get(num);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    imageLinkString = selectedCocktailObject.get("strDrinkThumb").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("MainActivity", imageLinkString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
package com.example.tutorial6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

import static com.example.tutorial6.Table.*;

public class MainActivity extends AppCompatActivity {
    final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    final String QUERY = "q";
    final String UNITS = "units";
    final String KEY = "appid";
    final String MAX_RESULTS = "maxResults";
    private URL requestURL;
    private HttpURLConnection connection;
    private String resultString;
    private Object currentTempObject;
    private Object feelsLikeTempObject;
    private Object sunriseObject;
    private Object sunsetObject;
    private JSONObject descriptionObject;
    private TextView cityNameTextview;
    private TextView currentTempTextView;
    private TextView feelsLikeTextView;
    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView cloudTextView;
    private EditText cityNameEditText;
    private String cityName;
    private String currentTemp;
    private String feelsLikeTemp;
    private String sunrise;
    private String sunset;
    private String description;
    private String userInput;
    public WeatherData weatherDataDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityNameTextview = findViewById(R.id.valuecity);
        currentTempTextView = findViewById(R.id.vtemp);
        feelsLikeTextView = findViewById(R.id.vfeelslike);
        sunriseTextView = findViewById(R.id.vsunrise);
        sunsetTextView = findViewById(R.id.vsunset);
        cloudTextView = findViewById(R.id.vcloud);
        cityNameEditText = findViewById(R.id.Entercity);
        weatherDataDatabase = new WeatherData(this);

    }

    public void Start(View view) {
        userInput = cityNameEditText.getText().toString();
        new Connection().execute();
    }

    public void History(View view) {
        userInput = cityNameEditText.getText().toString();
        Cursor cursor = AllWeatherHistory();
        AllDetails(cursor);
    }


    public Cursor AllWeatherHistory() {
        try {
            SQLiteDatabase db = weatherDataDatabase.getReadableDatabase();
            String query = "Select * FROM " + TABLE_NAME + " where weather_city_name LIKE '%" + userInput + "%'";
            Cursor cursor = db.rawQuery(query, null);
            return cursor;
        } catch (NullPointerException e) {
        }
        return null;
    }

    public void AllDetails(Cursor cursor) {
        StringBuilder builder = new StringBuilder("History " + userInput.toUpperCase() + ":\n");
        while (cursor.moveToNext()) {
            String weather_city_name = cursor.getString(1);
            String weather_date = cursor.getString(2);
            String weather_time = cursor.getString(3);
            String weather_temperature = cursor.getString(4);
            String weather_description = cursor.getString(5);

            builder.append("CityName : "+weather_city_name).append("\n");
            builder.append("Date : "+weather_date).append("\n");
            builder.append("Time :"+weather_time).append("\n");
            builder.append("Temprature :"+weather_temperature).append(""+"\n");
            builder.append("Cloud : "+weather_description).append("\n \n");

        }
        cursor.close();
        showMessage(builder);
    }

    private void showMessage(StringBuilder builder) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setCancelable(true);
        builder1.setMessage(builder);
        builder1.show();
    }


    private class Connection extends AsyncTask<URL, Integer, String> {
        @Override
        protected String doInBackground(URL... urls) {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY, userInput)
                    .appendQueryParameter(UNITS, "metric")
                    .appendQueryParameter(KEY, "7dd90432443eadb1acc262bcd0cdd6d3")
                    .appendQueryParameter(MAX_RESULTS, "1")
                    .build();
            try {
                requestURL = new URL(builtURI.toString());
                downUrl(String.valueOf(requestURL));
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
            return null;
        }

        private String downUrl(String url) throws IOException {
            InputStream inputStream = null;
            try {
                connection = (HttpURLConnection) requestURL.openConnection();
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);

                connection.connect();
                int response = connection.getResponseCode();
                inputStream = connection.getInputStream();


                String contentAsString = convertInputToString(inputStream);
                return contentAsString;

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

                cityName = (String) data.get("name");

                JSONObject main = data.getJSONObject("main");
                currentTempObject = main.get("temp");
                currentTemp = currentTempObject.toString() + "\u2103";

                feelsLikeTempObject = main.get("feels_like");
                feelsLikeTemp = feelsLikeTempObject.toString() + "\u2103";

                JSONObject sys = data.getJSONObject("sys");
                sunriseObject = sys.get("sunrise");
                long durationInMillis = Long.parseLong(sunriseObject.toString());
                long millis = durationInMillis % 1000;
                long second = (durationInMillis / 1000) % 60;
                long minute = (durationInMillis / (1000 * 60)) % 60;
                long hour = (durationInMillis / (1000 * 60 * 60)) % 24;
                String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
                sunrise = time;

                sunsetObject = sys.get("sunset");
                durationInMillis = Long.parseLong(sunsetObject.toString());
                millis = durationInMillis % 1000;
                second = (durationInMillis / 1000) % 60;
                minute = (durationInMillis / (1000 * 60)) % 60;
                hour = (durationInMillis / (1000 * 60 * 60)) % 24;
                time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
                sunset = time;

                JSONArray weatherArray = (JSONArray) data.get("weather");
                descriptionObject = (JSONObject) weatherArray.get(0);
                description = (descriptionObject.get("description")).toString();

                String CurrentDate = String.valueOf(java.time.LocalDate.now());
                String CurrentTime = String.valueOf(java.time.LocalTime.now());
                try {
                    addWeatherToDatabase(cityName, CurrentDate, CurrentTime, currentTemp, description);
                    AllWeatherHistory();
                } catch (NullPointerException e) {
                }

            } catch (JSONException e) {
            }
            return line;
        }

        public void addWeatherToDatabase(String cityName, String date, String time, String temperature, String description) {
            SQLiteDatabase db = weatherDataDatabase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CITY_NAME, cityName);
            values.put(DATE, date);
            values.put(TIME, time);
            values.put(TEMPERATURE, temperature);
            values.put(CLOUD, description);
            db.insert(TABLE_NAME, null, values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            cityNameTextview.setText(cityName);
            currentTempTextView.setText(currentTemp);
            feelsLikeTextView.setText(feelsLikeTemp);
            sunriseTextView.setText(sunrise);
            sunsetTextView.setText(sunset);
            cloudTextView.setText(description);
        }
    }

}
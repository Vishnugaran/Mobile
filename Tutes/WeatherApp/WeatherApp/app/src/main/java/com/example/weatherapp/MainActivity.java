package com.example.weatherapp;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.example.weatherapp.Constants.*;

public class MainActivity extends AppCompatActivity {
    final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    final String QUERY_PARAM = "q";
    final String UNITS = "units";
    final String KEY = "appid";
    final String MAX_RESULTS = "maxResults";
    private URL requestURL;
    private HttpURLConnection conn;
    private String resultString;
    private Object currentTempObject;
    private Object feelsLikeTempObject;
    private Object sunriseObject;
    private Object sunsetObject;
    private JSONObject descriptionObject;
    private TextView cityNameTextView;
    private TextView currentTempTextView;
    private TextView feelsLikeTextView;
    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView descriptionTextView;
    private TextView historyTextView;
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
        cityNameTextView = findViewById(R.id.textView_city_name_value);
        currentTempTextView = findViewById(R.id.textView_current_temperature_value);
        feelsLikeTextView = findViewById(R.id.textView_feels_like_value);
        sunriseTextView = findViewById(R.id.textView_sunrise_value);
        sunsetTextView = findViewById(R.id.textView_sunset_value);
        descriptionTextView = findViewById(R.id.textView_description_value);
        historyTextView = findViewById(R.id.textView_history_value);
        cityNameEditText = findViewById(R.id.editTextText_city_name_value);
        weatherDataDatabase = new WeatherData(this);
        displayBlank();
    }

    public void displayBlank() {
        cityNameTextView.setText("--");
        currentTempTextView.setText("--");
        feelsLikeTextView.setText("--");
        sunriseTextView.setText("--");
        sunsetTextView.setText("--");
        descriptionTextView.setText("--");
        historyTextView.setText("--");
    }


    public void findWeather(View view) {
        userInput = cityNameEditText.getText().toString();
        new Internet().execute();
    }

    public void displayHistory(View view) {
        userInput = cityNameEditText.getText().toString();
        Cursor cursor = getAllWeatherHistory();
        displayAllDetails(cursor);
    }

    public Cursor getAllWeatherHistory() {
        try {
            SQLiteDatabase db = weatherDataDatabase.getReadableDatabase();
            String query = "Select * FROM " + TABLE_NAME + " where weather_city_name LIKE '%" + userInput + "%'";
            Cursor cursor = db.rawQuery(query, null);
            return cursor;
        } catch (NullPointerException e) {}
        return null;
    }

    public void displayAllDetails(Cursor cursor) {
        StringBuilder builder = new StringBuilder("Weather History for "+ userInput.toUpperCase() +":\n");
        while (cursor.moveToNext()) {
            String weather_city_name = cursor.getString(1);
            String weather_date = cursor.getString(2);
            String weather_time = cursor.getString(3);
            String weather_temperature = cursor.getString(4);
            String weather_description = cursor.getString(5);
            builder.append(weather_city_name).append(" - ");
            builder.append(weather_date).append(" - ");
            builder.append(weather_time).append(" - ");
            builder.append(weather_temperature).append("\u2103 - ");
            builder.append(weather_description).append("\n");
        }
        cursor.close();
        historyTextView.setText(builder);
    }

    private class Internet extends AsyncTask<URL, Integer, String> {
        /*@Override
        protected void onPreExecute() {
            super.onPreExecute();
            cityNameTextView.setText("--");
            currentTempTextView.setText("--");
            feelsLikeTextView.setText("--");
            sunriseTextView.setText("--");
            sunsetTextView.setText("--");
            descriptionTextView.setText("--");
            historyTextView.setText("--");
        }*/

        @Override
        protected String doInBackground(URL... urls) {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, userInput)
                    .appendQueryParameter(UNITS, "metric")
                    .appendQueryParameter(KEY, "e144247afc67d3faa85ea4bfe9a9e093")
                    .appendQueryParameter(MAX_RESULTS, "1")
                    .build();
            try {
                requestURL = new URL(builtURI.toString());
                //System.out.println(requestURL);
                downloadUrl(String.valueOf(requestURL));
            } catch (MalformedURLException e) {}
            catch (IOException e) {}
            return null;
        }

        private String downloadUrl(String url) throws IOException {
            InputStream inputStream = null;
            try {
                conn = (HttpURLConnection) requestURL.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                // Start the query
                conn.connect();
                int response = conn.getResponseCode();
                inputStream = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = convertInputToString(inputStream);
                return contentAsString;

                // Close the InputStream and connection
            } finally {
                conn.disconnect();
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }

        // Reads an InputStream and converts it to a String.
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
                currentTemp = currentTempObject.toString()+"\u2103";

                feelsLikeTempObject = main.get("feels_like");
                feelsLikeTemp = feelsLikeTempObject.toString()+"\u2103";

                JSONObject sys = data.getJSONObject("sys");
                sunriseObject = sys.get("sunrise");
                long durationInMillis = Long.parseLong(sunriseObject.toString());
                long millis = durationInMillis % 1000;
                long second = (durationInMillis / 1000) % 60;
                long minute = (durationInMillis / (1000 * 60)) % 60;
                long hour = (durationInMillis / (1000 * 60 * 60)) % 24;
                String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
                sunrise = time;

                /*Date date = new Date(durationInMillis);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateFormatted = formatter.format(date);
                System.out.println(dateFormatted);*/

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
                    getAllWeatherHistory();
                } catch (NullPointerException e) {
                }

            } catch (JSONException e) {}
            return line;
        }

        public void addWeatherToDatabase(String cityName, String date, String time, String temperature, String description) {
            SQLiteDatabase db = weatherDataDatabase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(WEATHER_CITY_NAME, cityName);
            values.put(WEATHER_DATE, date);
            values.put(WEATHER_TIME, time);
            values.put(WEATHER_TEMPERATURE, temperature);
            values.put(WEATHER_DESCRIPTION, description);
            db.insert(TABLE_NAME, null, values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            cityNameTextView.setText(cityName);
            currentTempTextView.setText(currentTemp);
            feelsLikeTextView.setText(feelsLikeTemp);
            sunriseTextView.setText(sunrise);
            sunsetTextView.setText(sunset);
            descriptionTextView.setText(description);
            historyTextView.setText("--");
        }
    }
}
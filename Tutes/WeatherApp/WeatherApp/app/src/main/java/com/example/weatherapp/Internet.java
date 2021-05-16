
package com.example.weatherapp;

import android.net.Uri;
import android.os.AsyncTask;
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

/*
public class Internet extends AsyncTask<URL, Integer, String> {
    final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    final String QUERY_PARAM = "q";
    final String UNITS = "units";
    final String KEY = "appid";
    final String MAX_RESULTS = "maxResults";
    private URL requestURL;
    private HttpURLConnection conn;
    private String resultString;
    private Object tempObject;
    //final String PRINT_TYPE = "printType";
    private MainActivity activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Hello!!!");
    }

    @Override
    protected String doInBackground(URL... urls) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "London")
                .appendQueryParameter(UNITS, "metric")
                .appendQueryParameter(KEY, "e144247afc67d3faa85ea4bfe9a9e093")
                .appendQueryParameter(MAX_RESULTS, "1")
                .build();

        try {
            requestURL = new URL(builtURI.toString());
            downloadUrl(String.valueOf(requestURL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = "Hello";
        return message;

    }

    private String downloadUrl(String url) throws IOException {
        InputStream inputStream = null;
        try {
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000 */
/* milliseconds *//*
);
            conn.setConnectTimeout(15000 */
/* milliseconds *//*
);
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
        System.out.println(resultString);
        returnItems(resultString);
        //onPostExecute(resultString);
        return line;
    }

    public void returnItems(String result) {
        JSONObject data = null;
        try {
            data = new JSONObject(result);
            JSONObject main = data.getJSONObject("main");
            tempObject = main.get("temp");
            System.out.println(main);
            System.out.println(tempObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
*/

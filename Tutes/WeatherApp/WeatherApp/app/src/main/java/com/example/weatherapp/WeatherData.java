package com.example.weatherapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.weatherapp.Constants.*;

public class WeatherData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    /* Create a helper object for the employee database */
    public WeatherData(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WEATHER_CITY_NAME + " TEXT,"
                + WEATHER_DATE + " TEXT,"
                + WEATHER_TIME + " TEXT,"
                + WEATHER_TEMPERATURE + " TEXT,"
                + WEATHER_DESCRIPTION + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

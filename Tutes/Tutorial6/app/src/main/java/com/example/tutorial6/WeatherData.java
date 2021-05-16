package com.example.tutorial6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.provider.BaseColumns._ID;
import static com.example.tutorial6.Table.*;

public class WeatherData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;


    public WeatherData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CITY_NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + TEMPERATURE + " TEXT,"
                + CLOUD + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}


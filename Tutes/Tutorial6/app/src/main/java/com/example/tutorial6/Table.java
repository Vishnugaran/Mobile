package com.example.tutorial6;

import android.provider.BaseColumns;

public interface Table extends BaseColumns {
    public static final String TABLE_NAME = "weather";
    public static final String CITY_NAME = "weather_city_name";
    public static final String DATE = "weather_date";
    public static final String TIME = "weather_time";
    public static final String TEMPERATURE = "weather_temperature";
    public static final String CLOUD = "weather_cloud";
}


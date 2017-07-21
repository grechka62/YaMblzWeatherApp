package me.grechka.yamblz.yamblzweatherapp.repository;

import android.content.Context;
import android.content.SharedPreferences;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;

/**
 * Created by Grechka on 18.07.2017.
 */

public class PreferencesManager {
    private final String PREFERENCES_TAG = "weather_preferences";
    private final String FREQUENCY_TAG = "frequency";
    private final String FREQUENCY_DEFAULT = "60";
    private final String TEMPERATURE_TAG = "temperature";
    private final String DESCRIPTION_TAG = "descdription";
    private final String HUMIDITY_TAG = "humidity";
    private final String MIN_TEMP_TAG = "min_temp";
    private final String MAX_TEMP_TAG = "max_temp";
    private final String WIND_TAG = "wind";
    private final String NO_INFORMATION = "-";

    private SharedPreferences preferences;

    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE);
    }

    public String getUpdateFrequency() {
        return preferences.getString(FREQUENCY_TAG, FREQUENCY_DEFAULT);
    }

    public void putUpdateFrequency(String frequency) {
        preferences.edit().putString(FREQUENCY_TAG, frequency).apply();
    }

    public CurrentWeather getCurrentWeather() {
        return new CurrentWeather(
                preferences.getString(TEMPERATURE_TAG, NO_INFORMATION),
                preferences.getString(DESCRIPTION_TAG, NO_INFORMATION),
                preferences.getString(HUMIDITY_TAG, NO_INFORMATION),
                preferences.getString(MIN_TEMP_TAG, NO_INFORMATION),
                preferences.getString(MAX_TEMP_TAG, NO_INFORMATION),
                preferences.getString(WIND_TAG, NO_INFORMATION)
        );
    }

    public void putCurrentWeather(CurrentWeather currentWeather) {
        preferences.edit().putString(TEMPERATURE_TAG, currentWeather.temperature).apply();
        preferences.edit().putString(DESCRIPTION_TAG, currentWeather.description).apply();
        preferences.edit().putString(HUMIDITY_TAG, currentWeather.humidity).apply();
        preferences.edit().putString(MIN_TEMP_TAG, currentWeather.tempMin).apply();
        preferences.edit().putString(MAX_TEMP_TAG, currentWeather.tempMax).apply();
        preferences.edit().putString(WIND_TAG, currentWeather.wind).apply();
    }
}

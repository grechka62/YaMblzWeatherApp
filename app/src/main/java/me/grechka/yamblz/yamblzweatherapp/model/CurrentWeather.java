package me.grechka.yamblz.yamblzweatherapp.model;

/**
 * Created by Grechka on 16.07.2017.
 */

public class CurrentWeather {
    public final String temperature;
    public final String description;

    public CurrentWeather(String temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }
}

package me.grechka.yamblz.yamblzweatherapp.model;

/**
 * Created by Grechka on 16.07.2017.
 */

public class CurrentWeather {
    public final double temperature;
    public final String description;

    public CurrentWeather(double temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }
}

package me.grechka.yamblz.yamblzweatherapp.models;

/**
 * Created by Grechka on 16.07.2017.
 */

public class CurrentWeather {
    public final String temperature;
    public final String description;
    public final String humidity;
    public final String tempMin;
    public final String tempMax;
    public final String wind;

    public CurrentWeather(String temperature,
                          String description,
                          String humidity,
                          String tempMin,
                          String tempMax,
                          String wind) {
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.wind = wind;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrentWeather{");
        sb.append("temperature='").append(temperature).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", humidity='").append(humidity).append('\'');
        sb.append(", tempMin='").append(tempMin).append('\'');
        sb.append(", tempMax='").append(tempMax).append('\'');
        sb.append(", wind='").append(wind).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

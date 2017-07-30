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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentWeather weather = (CurrentWeather) o;

        if (temperature != null ? !temperature.equals(weather.temperature) : weather.temperature != null)
            return false;
        if (description != null ? !description.equals(weather.description) : weather.description != null)
            return false;
        if (humidity != null ? !humidity.equals(weather.humidity) : weather.humidity != null)
            return false;
        if (tempMin != null ? !tempMin.equals(weather.tempMin) : weather.tempMin != null)
            return false;
        if (tempMax != null ? !tempMax.equals(weather.tempMax) : weather.tempMax != null)
            return false;
        return wind != null ? wind.equals(weather.wind) : weather.wind == null;

    }

    @Override
    public int hashCode() {
        int result = temperature != null ? temperature.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (tempMin != null ? tempMin.hashCode() : 0);
        result = 31 * result + (tempMax != null ? tempMax.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        return result;
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

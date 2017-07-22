package me.grechka.yamblz.yamblzweatherapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public final class CurrentWeatherResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("coord")
    @Expose
    private Coordinates coordinates;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("main")
    @Expose
    private WeatherInfo weatherInfo;

    @SerializedName("visibility")
    @Expose
    private int visibility;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("dt")
    @Expose
    private long dt;

    @SerializedName("sys")
    @Expose
    private SunriseAndSunset sunriseAndSunset;

    @SerializedName("cod")
    @Expose
    private int cod;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public SunriseAndSunset getSunriseAndSunset() {
        return sunriseAndSunset;
    }

    public void setSunriseAndSunset(SunriseAndSunset sunriseAndSunset) {
        this.sunriseAndSunset = sunriseAndSunset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrentWeatherResponse{");
        sb.append("coordinates=").append(coordinates);
        sb.append(", weather=").append(weather);
        sb.append(", base='").append(base).append('\'');
        sb.append(", weatherInfo=").append(weatherInfo);
        sb.append(", visibility=").append(visibility);
        sb.append(", wind=").append(wind);
        sb.append(", clouds=").append(clouds);
        sb.append(", dt=").append(dt);
        sb.append(", sunriseAndSunset=").append(sunriseAndSunset);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cod=").append(cod);
        sb.append('}');
        return sb.toString();
    }
}

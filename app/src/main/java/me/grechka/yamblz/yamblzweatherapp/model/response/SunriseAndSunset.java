package me.grechka.yamblz.yamblzweatherapp.model.response;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class SunriseAndSunset implements Serializable {

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("message")
    @Expose
    private double message;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("sunrise")
    @Expose
    private long sunrise;

    @SerializedName("sunset")
    @Expose
    private long sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SunriseAndSunset{");
        sb.append("type=").append(type);
        sb.append(", id=").append(id);
        sb.append(", message=").append(message);
        sb.append(", country='").append(country).append('\'');
        sb.append(", sunrise=").append(sunrise);
        sb.append(", sunset=").append(sunset);
        sb.append('}');
        return sb.toString();
    }
}

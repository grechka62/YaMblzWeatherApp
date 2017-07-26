package me.grechka.yamblz.yamblzweatherapp.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public int getAll() {
        return all;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Clouds{");
        sb.append("all=").append(all);
        sb.append('}');
        return sb.toString();
    }
}

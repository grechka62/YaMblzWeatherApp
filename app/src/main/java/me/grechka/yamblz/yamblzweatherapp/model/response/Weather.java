package me.grechka.yamblz.yamblzweatherapp.model.response;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Weather implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weather{");
        sb.append("id=").append(id);
        sb.append(", main='").append(main).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

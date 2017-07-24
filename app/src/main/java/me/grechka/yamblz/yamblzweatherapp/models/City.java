package me.grechka.yamblz.yamblzweatherapp.models;

import android.support.annotation.NonNull;

import me.grechka.yamblz.yamblzweatherapp.models.response.CityLocation;

/**
 * Created by alexander on 23/07/2017.
 */

public class City {

    private String title;
    private String placeId;
    private String extendedTitle;
    private CityLocation location;

    private City(@NonNull Builder builder) {
        this.title = builder.title;
        this.placeId = builder.placeId;
        this.location = builder.location;
        this.extendedTitle = builder.extendedTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getExtendedTitle() {
        return extendedTitle;
    }

    public CityLocation getLocation() {
        return location;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("title='").append(title).append('\'');
        sb.append(", placeId='").append(placeId).append('\'');
        sb.append(", extendedTitle='").append(extendedTitle).append('\'');
        sb.append(", location=").append(location);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private String title;
        private String placeId;
        private String extendedTitle;
        private CityLocation location;

        public Builder() {
        }

        public Builder(@NonNull City city) {
            placeId = city.getPlaceId();
            title = city.getTitle();
            extendedTitle = city.getExtendedTitle();
            location = city.getLocation();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder placeId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Builder extendedTitle(String extendedTitle) {
            this.extendedTitle = extendedTitle;
            return this;
        }

        public Builder location(CityLocation location) {
            this.location = location;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}

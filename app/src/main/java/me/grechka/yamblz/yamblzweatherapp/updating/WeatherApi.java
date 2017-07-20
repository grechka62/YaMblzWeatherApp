package me.grechka.yamblz.yamblzweatherapp.updating;

import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grechka on 14.07.2017.
 */

public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<CurrentWeatherResponse> getCurrentWeather(@Query("id") String city,
                                           @Query("lang") String lang,
                                           @Query("units") String units,
                                           @Query("APPID") String key);
}

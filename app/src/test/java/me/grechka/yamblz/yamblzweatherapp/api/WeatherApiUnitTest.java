package me.grechka.yamblz.yamblzweatherapp.api;

import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.internal.matchers.GreaterThan;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import me.grechka.yamblz.yamblzweatherapp.models.response.Place;
import me.grechka.yamblz.yamblzweatherapp.models.response.Weather;
import me.grechka.yamblz.yamblzweatherapp.repository.net.SuggestApi;
import me.grechka.yamblz.yamblzweatherapp.repository.net.WeatherApi;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Matchers.intThat;

/**
 * Created by alexander on 27/07/2017.
 */

@RunWith(JUnit4.class)
public class WeatherApiUnitTest {

    private WeatherApi api;
    private MockWebServer mockWebServer;

    @Before
    public void createService() throws IOException {
        mockWebServer = new MockWebServer();

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();

        api = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .baseUrl(mockWebServer.url("/"))
                .build()
                .create(WeatherApi.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void SuggestApi_obtainWeatherForCoordinates_parsedCorrectlyWithGsonExposeAndCorrectModule()
            throws IOException, InterruptedException {
        enqueueResponse("openweather-weather.json");

        double latitude = 55.75;
        double longitude = 37.62;
        String units = "metric";
        String apiKey = "123456";

        api.getWeatherByLocation(latitude, longitude, units, apiKey)
                .subscribe(weather -> {
                    assertThat(weather.getWeather().size(), new GreaterThan<>(0));
                    Weather w = weather.getWeather().get(0);

                    assertEquals(26.76, weather.getWeatherInfo().getTemp(), 0.01);
                    assertEquals("clear sky", w.getDescription());
                    assertEquals(51, weather.getWeatherInfo().getHumidity());
                    assertEquals(28.0, weather.getWeatherInfo().getTempMax(), 0.01);
                    assertEquals(26.0, weather.getWeatherInfo().getTempMin(), 0.01);
                    assertEquals(5.0, weather.getWind().getSpeed(), 0.01);
                });

        RecordedRequest request = mockWebServer.takeRequest();
        String url = String.format("/data/2.5/weather?lat=%1$.2f&lon=%2$.2f&units=metric&appid=%3$s",
                latitude, longitude, apiKey);
        assertEquals(request.getPath(), url);
    }


    private void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.emptyMap());
    }

    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            mockResponse.addHeader(header.getKey(), header.getValue());
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(Charset.forName("UTF-8"))));
    }
}

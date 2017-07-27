package me.grechka.yamblz.yamblzweatherapp.api;

import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import me.grechka.yamblz.yamblzweatherapp.models.response.Place;
import me.grechka.yamblz.yamblzweatherapp.repository.net.SuggestApi;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexander on 27/07/2017.
 */

@RunWith(JUnit4.class)
public class SuggestApiUnitTest {

    private SuggestApi api;
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
                .create(SuggestApi.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void SuggestApi_obtainPlacesSuggestion_parsedCorrectlyWithGsonExposeAndCorrectModule()
            throws IOException, InterruptedException {
        enqueueResponse("places-suggestion.json");

        String input = "San-Jose";
        String type = SuggestApi.API_TYPES;
        String apiKey = "123456";

        api.obtainSuggestedCities(input, type, apiKey).subscribe(response -> {
            assertEquals(2, response.getPredictions().size());

            Place sanJoseUs = response.getPredictions().get(0);

            assertEquals("ChIJ9T_5iuTKj4ARe3GfygqMnbk", sanJoseUs.getPlaceId());
            assertEquals("San Jose, CA, United States", sanJoseUs.getDescription());
            assertEquals("San Jose", sanJoseUs.getPlaceInfo().getMainText());

            Place sanJoseCr = response.getPredictions().get(1);

            assertEquals("ChIJxRUNxULjoI8RgrgRn2pqdOY", sanJoseCr.getPlaceId());
            assertEquals("San Jose, San José Province, Costa Rica", sanJoseCr.getDescription());
            assertEquals("San Jose", sanJoseCr.getPlaceInfo().getMainText());
        });

        RecordedRequest request = mockWebServer.takeRequest();
        String urlEncodedInput = URLEncoder.encode(input, "UTF-8");
        String url = String.format("/autocomplete/json?input=%1$s&types=%2$s&key=%3$s",
                urlEncodedInput, type, apiKey);
        assertEquals(request.getPath(), url);
    }

    @Test
    public void SuggestApi_obtain_parsedCorrectlyWithGsonExposeAndCorrectModule()
            throws IOException, InterruptedException {
        enqueueResponse("places-city.json");

        String placeId = "ChIJxRUNxULjoI8RgrgRn2pqdOY";
        String apiKey = "123456";

        api.obtainCity(placeId, apiKey).subscribe(city -> {
            System.out.println(city.toString());
            assertEquals(9.9280694, city.getInfo().getGeometry().getLocation().getLatitude(), 0.00000001);
            assertEquals(-84.0907246, city.getInfo().getGeometry().getLocation().getLongitude(), 0.00000001);
        });

        RecordedRequest request = mockWebServer.takeRequest();
        String url = String.format("/details/json?placeid=%1$s&key=%2$s",
                placeId, apiKey);
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

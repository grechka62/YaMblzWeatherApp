package me.grechka.yamblz.yamblzweatherapp.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by alexander on 27/07/2017.
 */

public class JsonProvider {

    private static Gson gson = new Gson();

    private JsonProvider() {
    }

    public static <T> T openFile(@NonNull Class<T> to,
                                        @NonNull String fileName) {
        InputStream inputStream = to.getClassLoader().getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, to);
    }
}

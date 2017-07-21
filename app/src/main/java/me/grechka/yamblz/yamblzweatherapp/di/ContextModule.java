package me.grechka.yamblz.yamblzweatherapp.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grechka on 18.07.2017.
 */

@Module
public class ContextModule {

    private Context appContext;

    public ContextModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @NonNull
    @Singleton
    Context provideContext() {
        return appContext;
    }
}

package me.grechka.yamblz.yamblzweatherapp.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.interactor.InteractorImp;
import me.grechka.yamblz.yamblzweatherapp.repository.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.repository.RepositoryImp;

/**
 * Created by Grechka on 19.07.2017.
 */

@Module
public class DataModule {

    @Provides
    @NonNull
    @Singleton
    public Repository provideRepository() {
        return new RepositoryImp();
    }

    @Provides
    @NonNull
    @Singleton
    public PreferencesManager providePreferencesManager(Context context) {
        return new PreferencesManager(context);
    }

    @Provides
    @NonNull
    @Singleton
    public Interactor provideInteractor() {
        return new InteractorImp();
    }
}

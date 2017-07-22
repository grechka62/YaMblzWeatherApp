package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Singleton;

import me.grechka.yamblz.yamblzweatherapp.repository.Repository;

/**
 * Created by alexander on 22/07/2017.
 */

@Singleton
@InjectViewState
public class CitySearchPresenter extends MvpPresenter<CitySearchView> {

    private static final String TAG = CitySearchPresenter.class.getCanonicalName();

    private Repository appRepository;

    public CitySearchPresenter(@NonNull Repository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void attachView(CitySearchView view) {
        super.attachView(view);

        this.appRepository.obtainSuggestedCities("Mos")
                .subscribe(w -> Log.d(TAG, w.toString()));
    }
}

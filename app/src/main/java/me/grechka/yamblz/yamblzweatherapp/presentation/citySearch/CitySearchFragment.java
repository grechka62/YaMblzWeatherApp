package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;

/**
 * Created by alexander on 22/07/2017.
 */

public class CitySearchFragment extends MvpAppCompatFragment implements CitySearchView {

    @Inject
    @InjectPresenter
    CitySearchPresenter presenter;

    @ProvidePresenter
    CitySearchPresenter providePresenter() {
        return presenter;
    }

    public static CitySearchFragment newInstance() {
        return new CitySearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeatherApp.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_city_search, container, false);
        onInit();
        return v;
    }

    private void onInit() {
    }
}

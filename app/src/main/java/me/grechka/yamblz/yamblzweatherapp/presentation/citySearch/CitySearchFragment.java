package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.grechka.yamblz.yamblzweatherapp.R;

/**
 * Created by alexander on 22/07/2017.
 */

public class CitySearchFragment extends MvpAppCompatFragment implements CitySearchView {

    public static CitySearchFragment newInstance() {
        return new CitySearchFragment();
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

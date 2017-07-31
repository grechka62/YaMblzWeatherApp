package me.grechka.yamblz.yamblzweatherapp.presentation.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.events.OnDismissDialogListener;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.presentation.citySearch.CitySearchFragment;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView,
        OnDismissDialogListener,
        SwipeRefreshLayout.OnRefreshListener{

    private View view;
    private TextView tempView;
    private TextView maxTempView;
    private TextView minTempView;
    private TextView descView;
    private TextView windView;
    private TextView humidityView;
    private TextView cityTitleTextView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Inject Context context;
    @Inject MainPresenter mainPresenter;

    @InjectPresenter WeatherPresenter presenter;

    @ProvidePresenter
    public WeatherPresenter providePresenter() {
        return WeatherApp
                .getComponent()
                .getWeatherPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        WeatherApp.getComponent().inject(this);

        cityTitleTextView = (TextView) view.findViewById(R.id.city);
        tempView = (TextView) view.findViewById(R.id.cur_temp);
        maxTempView = (TextView) view.findViewById(R.id.temp_max);
        minTempView = (TextView) view.findViewById(R.id.temp_min);
        descView = (TextView) view.findViewById(R.id.description);
        windView = (TextView) view.findViewById(R.id.wind_value);
        humidityView = (TextView) view.findViewById(R.id.humidity_value);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.showSavedCurrentWeather();

        return view;
    }

    @Override
    public void showCurrentWeather(String temperature,
                                   String description,
                                   String humidity,
                                   String tempMin,
                                   String tempMax,
                                   String wind) {
        tempView.setText(temperature);
        maxTempView.setText(tempMax);
        minTempView.setText(tempMin);
        descView.setText(description);
        windView.setText(wind);
        humidityView.setText(humidity);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showCity(@NonNull City city) {
        cityTitleTextView.setText(city.getTitle());
        //cityTitleHeaderTextView.setText(city.getTitle());
        //cityAreaHeaderTextView.setText(city.getExtendedTitle());
    }

    @Override
    public void showCitySearch() {
        CitySearchFragment.newInstance().show(getChildFragmentManager(), null);
    }

    @Override
    public void onDialogDismissed() {
        closeDrawer();
        presenter.updateCity();
        presenter.updateCurrentWeather();
    }

    @Override
    public void showMessage(String message) {
        if (message.equals("Error")) message = getResources().getString(R.string.error);
        else if (message.equals("No network")) message = getResources().getString(R.string.no_network);
        swipeRefreshLayout.setRefreshing(false);
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    @Override
    public void onRefresh() {
        presenter.updateCurrentWeather();
    }

    @Override
    public void closeDrawer() {
        //drawerLayout.closeDrawer(GravityCompat.START);
    }
}

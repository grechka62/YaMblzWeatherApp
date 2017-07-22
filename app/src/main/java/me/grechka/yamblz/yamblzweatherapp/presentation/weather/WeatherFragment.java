package me.grechka.yamblz.yamblzweatherapp.presentation.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainPresenter;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView,
        NavigationView.OnNavigationItemSelectedListener,
        SwipeRefreshLayout.OnRefreshListener{
    private View view;
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectPresenter
    WeatherPresenter presenter;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        WeatherApp.getComponent().inject(this);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        View headerRootView = navigationView.getHeaderView(0).findViewById(R.id.fragment_weather_header_root);
        headerRootView.setOnClickListener(v -> mainPresenter.showCitySearch());
        navigationView.setNavigationItemSelectedListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.showSavedCurrentWeather();

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mainPresenter.navigate(item.getItemId());
        presenter.closeDrawer();
        return true;
    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showCurrentWeather(String temperature,
                                   String description,
                                   String humidity,
                                   String tempMin,
                                   String tempMax,
                                   String wind) {
        TextView tempView = (TextView) view.findViewById(R.id.cur_temp);
        tempView.setText(temperature);
        TextView maxTempView = (TextView) view.findViewById(R.id.temp_max);
        maxTempView.setText(tempMax);
        TextView minTempView = (TextView) view.findViewById(R.id.temp_min);
        minTempView.setText(tempMin);
        TextView descView = (TextView) view.findViewById(R.id.description);
        descView.setText(description);
        TextView windView = (TextView) view.findViewById(R.id.wind_value);
        windView.setText(wind);
        TextView humidityView = (TextView) view.findViewById(R.id.humidity_value);
        humidityView.setText(humidity);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        if (message.compareTo("Error") == 0) message = getResources().getString(R.string.error);
        else if (message.compareTo("No network") == 0) message = getResources().getString(R.string.no_network);
        swipeRefreshLayout.setRefreshing(false);
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null)
            v.setGravity(Gravity.CENTER);
        toast.show();
    }

    @Override
    public void onRefresh() {
        presenter.updateCurrentWeather();
    }
}

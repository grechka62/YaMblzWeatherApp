package me.grechka.yamblz.yamblzweatherapp.presentation.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import me.grechka.yamblz.yamblzweatherapp.presentation.AboutFragment;
import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.presentation.citySearch.CitySearchFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.settings.SettingsFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.weather.WeatherFragment;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return WeatherApp.getComponent().getMainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            presenter.showWeather();
    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.goBack();
        return true;
    }

    @Override
    public void onBackPressed() {
        presenter.goBack();
    }

    @Override
    public void showWeather() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new WeatherFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void showSettings() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void showAbout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AboutFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void showCitySearch() {
        CitySearchFragment.newInstance().show(getSupportFragmentManager(), null);
    }

    @Override
    public void navigate(int screenId) {
        switch (screenId) {
            case R.id.nav_settings:
                presenter.showSettings();
                break;
            case R.id.nav_about:
                presenter.showAbout();
                break;
        }
    }

    @Override
    public void goBack() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ((drawer != null) && (drawer.isDrawerOpen(GravityCompat.START)))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

}

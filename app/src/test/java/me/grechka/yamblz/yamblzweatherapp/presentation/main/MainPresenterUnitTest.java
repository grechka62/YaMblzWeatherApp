package me.grechka.yamblz.yamblzweatherapp.presentation.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import me.grechka.yamblz.yamblzweatherapp.base.BaseUnitTest;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainView;

import static org.mockito.Mockito.*;

/**
 * Created by alexander on 30/07/2017.
 */

public class MainPresenterUnitTest extends BaseUnitTest {

    private MainPresenter presenter = new MainPresenter();

    @Mock MainView view;

    @Before
    @Override
    public void onInit() {
        super.onInit();
        presenter.attachView(view);
    }

    @Override
    public void onMockInit() {
    }

    @Test
    public void mainPresenter_navigateToWeather_success() {
        presenter.showWeather();
        verify(view, only()).showWeather();
    }

    @Test
    public void mainPresenter_navigateToSettings_success() {
        presenter.showSettings();
        verify(view, only()).showSettings();
    }

    @Test
    public void mainPresenter_navigateToAbout_success() {
        presenter.showAbout();
        verify(view, only()).showAbout();
    }

    @Test
    public void mainPresenter_pressBack_success() {
        presenter.goBack();
        verify(view, only()).goBack();
    }

    @Test
    public void mainPresenter_navigateById_success() {
        presenter.navigate(anyInt());
        verify(view, only()).navigate(anyInt());
    }
}

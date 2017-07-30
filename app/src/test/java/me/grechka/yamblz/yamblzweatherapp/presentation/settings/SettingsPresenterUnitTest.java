package me.grechka.yamblz.yamblzweatherapp.presentation.settings;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import me.grechka.yamblz.yamblzweatherapp.base.BaseUnitTest;
import me.grechka.yamblz.yamblzweatherapp.repository.prefs.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.schedule.WeatherJobUtils;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by alexander on 30/07/2017.
 */

public class SettingsPresenterUnitTest extends BaseUnitTest {

    private SettingsPresenter presenter;

    @Mock SettingsView view;
    @Mock WeatherJobUtils utils;
    @Mock PreferencesManager preferencesManager;

    @Before
    @Override
    public void onInit() {
        super.onInit();

        presenter = new SettingsPresenter(utils, preferencesManager);
        presenter.attachView(view);
    }

    @Override
    public void onMockInit() {

    }

    @Test
    public void SettingsPresenter_changeUpdateSchedule_success() {
        presenter.changeUpdateSchedule(anyInt());

        verify(preferencesManager, times(1)).putUpdateFrequency(anyString());
        verify(utils, times(1)).rescheduleWeatherJob(anyInt());
    }

    @Test
    public void SettingsPresenter_updateFrequency_success() {
        presenter.getUpdateFrequency();

        verify(preferencesManager, times(1)).getUpdateFrequency();
    }
}

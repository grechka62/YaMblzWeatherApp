package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.events.OnItemClickListener;
import me.grechka.yamblz.yamblzweatherapp.events.StopTypingDetector;
import me.grechka.yamblz.yamblzweatherapp.models.City;

/**
 * Created by alexander on 22/07/2017.
 */

public class CitySearchFragment extends MvpAppCompatDialogFragment
        implements CitySearchView,
        OnItemClickListener<City>,
        StopTypingDetector.TypingListener {

    public interface OnDismissListener {
        void onDismissDialog();
    }

    private View rootView;
    private EditText searchEditText;
    private RecyclerView suggestRecyclerView;

    private OnDismissListener listener;

    private Handler handler = new Handler();
    private CitySearchAdapter adapter = new CitySearchAdapter();
    private LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

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
        listener = (OnDismissListener) getParentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.SearchCityDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_city_search, container, false);
        onInit(rootView);
        return rootView;
    }

    private void onInit(View v) {
        searchEditText = (EditText) v.findViewById(R.id.fragment_city_search_edittext);
        suggestRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_city_search_recycler_view);

        suggestRecyclerView.setLayoutManager(layoutManager);
        suggestRecyclerView.setAdapter(adapter);

        adapter.setListener(this);
        searchEditText.addTextChangedListener(new StopTypingDetector(handler, this));
    }

    @Override
    public void addSuggestion(City suggestion) {
        adapter.add(suggestion);
    }

    @Override
    public void clearSuggestions() {
        adapter.clear();
    }

    @Override
    public void closeSelf() {
        dismiss();
    }

    @Override
    public void onClick(City item, int position) {
        presenter.loadCity(item);
    }

    @Override
    public void onStopTyping() {
        clearSuggestions();
        presenter.loadSuggestions(searchEditText.getText().toString());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        listener.onDismissDialog();
    }
}

package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.events.OnItemClickListener;
import me.grechka.yamblz.yamblzweatherapp.models.City;

/**
 * Created by alexander on 23/07/2017.
 */

public class CitySearchAdapter extends RecyclerView.Adapter {

    private List<City> list;
    private OnItemClickListener<City> listener;

    public CitySearchAdapter() {
        list = new ArrayList<>();
    }

    public void addAll(@NonNull Collection<City> collection) {
        this.list.addAll(collection);
    }

    public void setListener(OnItemClickListener<City> listener) {
        this.listener = listener;
    }

    public class CitySearchViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        public CitySearchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) return;
            int position = getAdapterPosition();
            listener.onClick(list.get(position), position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_city, parent, false);
        return new CitySearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

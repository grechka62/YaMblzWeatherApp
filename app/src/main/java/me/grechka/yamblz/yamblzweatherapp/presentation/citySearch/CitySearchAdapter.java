package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.events.OnItemClickListener;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import sasd97.java_blog.xyz.circleview.CircleView;

/**
 * Created by alexander on 23/07/2017.
 */

public class CitySearchAdapter extends RecyclerView.Adapter<CitySearchAdapter.CitySearchViewHolder> {

    private OnItemClickListener<City> listener;
    private List<City> cities = new ArrayList<>();

    public void setListener(OnItemClickListener<City> listener) {
        this.listener = listener;
    }

    class CitySearchViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private View clickRegionView;

        private TextView cityArea;
        private TextView cityTitle;
        private CircleView cityLogoHolder;

        CitySearchViewHolder(View itemView) {
            super(itemView);

            cityTitle = (TextView) itemView.findViewById(R.id.row_city_title);
            cityArea = (TextView) itemView.findViewById(R.id.row_city_description);
            cityLogoHolder = (CircleView) itemView.findViewById(R.id.row_city_alias);
            clickRegionView = itemView.findViewById(R.id.row_city_clickable_area);

            clickRegionView.setOnClickListener(this);
        }

        public void setCity(@NonNull City city) {
            cityTitle.setText(city.getTitle());
            cityArea.setText(city.getExtendedTitle());
            cityLogoHolder.setText(city.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (listener == null) return;
            int position = getAdapterPosition();
            listener.onClick(cities.get(position), position);
        }
    }

    @Override
    public CitySearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_city, parent, false);
        return new CitySearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CitySearchViewHolder holder, int position) {
        City city = cities.get(position);
        holder.setCity(city);
    }

    public void add(@NonNull City item) {
        this.cities.add(item);
        notifyItemInserted(getItemCount());
    }

    public void clear() {
        int oldLength = getItemCount();
        this.cities.clear();
        notifyItemRangeRemoved(0, oldLength);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}

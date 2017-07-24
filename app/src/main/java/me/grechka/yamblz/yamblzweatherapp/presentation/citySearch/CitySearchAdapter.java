package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.events.OnItemClickListener;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import sasd97.java_blog.xyz.circleview.CircleView;

/**
 * Created by alexander on 23/07/2017.
 */

public class CitySearchAdapter extends RecyclerView.Adapter<CitySearchAdapter.CitySearchViewHolder> {

    private List<City> list;
    private OnItemClickListener<City> listener;

    public CitySearchAdapter() {
        list = new ArrayList<>();
    }

    public void add(@NonNull City item) {
        this.list.add(item);
        notifyItemInserted(getItemCount());
    }

    public void addAll(@NonNull Collection<City> collection) {
        int oldSize = getItemCount();
        this.list.addAll(collection);
        notifyItemRangeInserted(oldSize, getItemCount());
    }

    public void clear() {
        int oldLength = getItemCount();
        this.list.clear();
        notifyItemRangeRemoved(0, oldLength);
    }

    public void setListener(OnItemClickListener<City> listener) {
        this.listener = listener;
    }

    public class CitySearchViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView cityTitle;
        private CircleView cityAlias;
        private TextView cityDescription;

        public CitySearchViewHolder(View itemView) {
            super(itemView);

            cityTitle = (TextView) itemView.findViewById(R.id.row_city_title);
            cityDescription = (TextView) itemView.findViewById(R.id.row_city_description);
            cityAlias = (CircleView) itemView.findViewById(R.id.row_city_alias);

            itemView.setOnClickListener(this);
        }

        public void setCity(@NonNull City city) {
            cityTitle.setText(city.getTitle());
            cityDescription.setText(city.getExtendedTitle());
            cityAlias.setText(city.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (listener != null) return;
            int position = getAdapterPosition();
            listener.onClick(list.get(position), position);
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
        City city = list.get(position);
        holder.setCity(city);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

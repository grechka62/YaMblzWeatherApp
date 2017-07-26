package me.grechka.yamblz.yamblzweatherapp.events;

/**
 * Created by alexander on 23/07/2017.
 */

public interface OnItemClickListener<T> {
    void onClick(T item, int position);
}

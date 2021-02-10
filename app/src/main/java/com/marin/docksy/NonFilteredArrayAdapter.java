package com.marin.docksy;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;

import java.util.List;

public class NonFilteredArrayAdapter<T> extends ArrayAdapter<T> {

    private Context context;
    private int layout;
    private List<T> values;

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            results.values = values;
            results.count = values.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    };

    public NonFilteredArrayAdapter(Context context, int layout, List<T> values) {
        super(context, layout, values);
        this.context = context;
        this.layout = layout;
        this.values = values;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }
}
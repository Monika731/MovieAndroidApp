package com.example.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter implements ListAdapter {
    ArrayList<MovieData> movieData;
    Context context;
    public CustomAdapter(Context context, ArrayList<MovieData> arrayList) {
        this.movieData = arrayList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return movieData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieData movies = movieData.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_row, null);

            TextView title = convertView.findViewById(R.id.textView);
            ImageView imag = convertView.findViewById(R.id.imageView);
            TextView year = convertView.findViewById(R.id.textView2);

            title.setText(context.getString(R.string.set_title) + movies.title);
            year.setText(context.getString(R.string.set_year)+movies.year);
            Picasso.with(context)
                    .load(movies.poster)
                    .placeholder(R.drawable.ic_no_image)
                    .into(imag);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return movieData.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    NewsAdapter(@NonNull Context context, @NonNull News[] newsArray) {
        super(context, 0, newsArray);
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        News news = getItem(position);
        Log.d(LOG_TAG, " getting item");

        // Populate the data into the view using the data object
        assert news != null;
        viewHolder.titleTextView.setText(news.getTitle());
        viewHolder.sectionTextView.setText(news.getSection());
        viewHolder.urlTextView.setText(news.getUrl());


        // Return the completed view to render on screen
        return convertView;
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView sectionTextView;
        TextView urlTextView;

        ViewHolder(View view) {
            this.titleTextView = view.findViewById(R.id.list_item_title);
            this.sectionTextView = view.findViewById(R.id.list_item_section);
            this.urlTextView = view.findViewById(R.id.list_item_url);
        }
    }


}

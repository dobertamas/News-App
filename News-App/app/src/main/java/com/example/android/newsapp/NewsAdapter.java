package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull News[] newsArray) {
        super(context, 0, newsArray);
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView urlTextView;

        ViewHolder(View view) {
            this.titleTextView = view.findViewById(R.id.list_item_title);
            this.urlTextView = view.findViewById(R.id.list_item_url);
        }
    }


}

package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.list) ListView mListView;

    News[] mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mNewsList = new News[]{new News("title1", "url1"), new News("title2", "url2")};

        NewsAdapter newsAdapter = new NewsAdapter(this, 0, mNewsList);

        mListView.setAdapter(newsAdapter);


    }

    @Override public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<List<News>>(MainActivity.this) {
            @Override public List<News> loadInBackground() {
                return null;
            }
        };
    }

    @Override public void onLoadFinished(Loader<List<News>> loader, List<News> newses) {

    }

    @Override public void onLoaderReset(Loader<List<News>> loader) {

    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL" + urlString, exception);
            return null;
        }
        return url;
    }


}

package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

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
}

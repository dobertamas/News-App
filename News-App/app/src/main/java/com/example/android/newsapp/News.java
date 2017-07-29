package com.example.android.newsapp;

public class News {

    private String mTitle;
    private String mUrl;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public News(String title, String url) {
        mTitle = title;
        mUrl = url;
    }
}

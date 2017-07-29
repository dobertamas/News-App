package com.example.android.newsapp;

class News {

    private String mTitle;
    private String mUrl;

    String getTitle() {
        return mTitle;
    }

    /*public void setTitle(String title) {
        mTitle = title;
    }*/

    String getUrl() {
        return mUrl;
    }

    /*public void setUrl(String url) {
        mUrl = url;
    }*/

    News(String title, String url) {
        mTitle = title;
        mUrl = url;
    }
}

package com.example.android.newsapp;

class News {

    private String mTitle;
    private String mUrl;
    private String mSection;

    News() {
    }

    String getTitle() {
        return mTitle;
    }

    String getUrl() {
        return mUrl;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    void setUrl(String url) {
        mUrl = url;
    }

    News(String title, String url, String section) {
        mTitle = title;
        mUrl = url;
        mSection = section;
    }

    String getSection() {
        return mSection;
    }

    void setSection(String section) {
        mSection = section;
    }
}

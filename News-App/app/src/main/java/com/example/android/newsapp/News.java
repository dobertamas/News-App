package com.example.android.newsapp;

class News {

    private String mTitle;
    private String mUrl;
    private String mSection;

    public News() {
    }

    String getTitle() {
        return mTitle;
    }

    String getUrl() {
        return mUrl;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    News(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }
}
